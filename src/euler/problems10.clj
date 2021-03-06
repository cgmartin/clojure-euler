(ns euler.problems10
  (:use euler.util)
  (:require [clojure.math.numeric-tower :as math])
  (:require [clojure.math.combinatorics :as combo]))

; For details on a particular problem, go to:
; http://projecteuler.net/problem=1

; REPL
; (require '[clojure.math.numeric-tower :as math])
; (require '[clojure.math.combinatorics :as combo])


; Problem 10 : Summation of primes
(defn problem10
  "Summation of primes"
  [n]
  (reduce +
    (take-while #(< % n) (prime-sieve))))


; Problem 11 : Largest product in a grid
(defn problem11
  "Largest product in a grid"
  [n grid-size file]
  (let [grid (vec (partition grid-size (map #(Integer. %) (re-seq #"\d\d" (slurp file)))))]
    (apply max
      (for [col (range grid-size)
            row (range grid-size)
            delta-yx [[1 0] [1 1] [0 1] [-1 1]]] ; is not xy due to vector indexing
        (apply *
          (for [yx (take n (iterate #(map + delta-yx %) [row col]))
                :while (every? #(< -1 % grid-size) yx)]
            (reduce nth grid yx)))))))


; Problem 12 : Highly divisible triangular number
(defn problem12
  "Highly divisible triangular number"
  [n]
  (first
    (drop-while #(> n (num-divisors %)) (triangle-nums))))


; Problem 13 : Large sum
(defn problem13
  "Large sum"
  [n file]
  (let [numbers (partition n (map #(Integer. %) (re-seq #"\d" (slurp file))))]
    (reduce #(list-add %1 %2) numbers)))

; Problem 14 : Longest Collatz sequence
(defn- collatz [n]
  (if (even? n)
    (/ n 2)
    (+ (* 3 n) 1)))

(defn- collatz-seq-count [n]
  (loop [n n
         i 0]
    (if (<= n 1)
      (inc i)
      (recur (collatz n) (inc i)))))

(defn problem14
  "Longest Collatz sequence"
  [n]
  (last
    (reduce
      #(if (> (first %1) (first %2)) %1 %2)
      (map #(vec [(collatz-seq-count %) %]) (range n)))))

; Problem 15 : Lattice paths
; https://en.wikipedia.org/wiki/Pascal's_triangle
(def num-grid-paths
  (memoize (fn [x y]
             (if (or (= x 0) (= y 0))
               1
               (+ (num-grid-paths (dec x) y)
                 (num-grid-paths x (dec y)))))))

(defn problem15
  "Lattice paths"
  [grid-size]
  (num-grid-paths grid-size grid-size))

; Problem 16 : Power digit sum
(defn problem16
  "Power digit sum"
  [base pow]
  (loop [n (math/expt base pow)
         acc 0]
    (if (< n 10)
      (+ n acc)
      (recur (quot n 10) (+ acc (rem n 10))))))

; Problem 17 : Number letter counts
; http://en.wikipedia.org/wiki/English_numerals
(def num-words-lower
  ["zero" "one" "two" "three" "four" "five" "six" "seven" "eight" "nine"
   "ten" "eleven" "twelve" "thirteen" "fourteen" "fifteen" "sixteen" "seventeen"
   "eighteen" "nineteen"])
(def num-words-upper
  [nil nil "twenty" "thirty" "forty" "fifty" "sixty" "seventy" "eighty" "ninety"])

(defn- num-to-words
  [n]
  (cond
    (< n 20)              (num-words-lower n)
    (and
      (< n 100)
      (zero? (rem n 10))) (num-words-upper (quot n 10))
    (< n 100)             (str (num-to-words (- n (rem n 10))) "-" (num-to-words (rem n 10)))
    (< n 1000)            (str (num-to-words (quot n 100)) " hundred"
                            (when (> (rem n 100) 0)
                              (str " and " (num-to-words (rem n 100)))))
    (= n 1000)            "one thousand"))

(defn- count-letters
  [s]
  (count (re-seq #"[a-zA-Z]" s)))

(defn problem17
  "Number letter counts"
  [n m]
  (reduce +
    (map #(count-letters (num-to-words %)) (range n (+ m 1)))))


; Problem 18 : Maximum path sum I
(defn- reduce-max-triangle
  [a b]
  (vec
    (for [i (range (count b))]
      (+ (max (a i) (a (inc i))) (b i)))))

(defn problem18
  "Maximum path sum I"
  [file]
  (let [triangle (for [row (.split (slurp file) "\n")]
                   (vec (map #(Integer. %) (.split row "\\s"))))]
    (first
      (reduce reduce-max-triangle (reverse triangle)))))


; Problem 19 : Counting Sundays
(defn leap-year?
  [year]
  (cond
    (zero? (rem year 100)) (if (zero? (rem year 400)) true false)
    (zero? (rem year 4))   true
    :else                  false))

(defn- days-in-year
  [year]
  (if (leap-year? year) 366 365))

(defn- num-days-on-first-of-month
  [day year]
  (let [start-day  (rem (reduce + (map #(days-in-year %) (range 1900 year))) 7) ; Mon = 0
        day-sub    (- day start-day)
        day-offset (if (< day-sub 0) (+ 6 day-sub) day-sub)
        feb        (if (leap-year? year) 29 28)
        days       [31 feb 31 30 31 30 31 31 30 31 30 31]
        month-offsets (map
                        #(reduce + (map days (range %)))
                        (range 12))]
    (reduce + (map #(if (= (rem % 7) day-offset) 1 0) month-offsets))))

(defn problem19
  "Counting Sundays"
  [start-year end-year day]
  (reduce + (map #(num-days-on-first-of-month day %) (range start-year (inc end-year)))))

