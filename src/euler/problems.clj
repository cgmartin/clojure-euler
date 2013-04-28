(ns euler.problems
  (:use euler.util)
  (:require [clojure.math.numeric-tower :as math])
  (:require [clojure.math.combinatorics :as combo]))

; For details on a particular problem, go to:
; http://projecteuler.net/problem=1

; REPL
; (require '[clojure.math.numeric-tower :as math])
; (require '[clojure.math.combinatorics :as combo])


; Problem 1 : Multiples of 3 and 5
(letfn [(multiple-3-5? [n]
          (or (multiple? n 3) (multiple? n 5)))]
  (defn problem1
    "Sum of multiples 3 and 5"
    [n]
    (reduce + (filter multiple-3-5? (range n)))))


; Problem 2 : Even Fibonacci numbers
(defn problem2
  "Even Fibonacci numbers"
  [n]
  (reduce +
    (filter even?
      (take-while #(<= % n) (fib 1 2)))))


; Problem 3 : Largest prime factor
; http://en.wikipedia.org/wiki/Trial_division
(defn problem3
  "Largest prime factor"
  [n]
  (last (prime-factors n)))


; Problem 4 : Largest palindrome product
; http://clojuredocs.org/clojure_core/clojure.core/for
(defn problem4
  "Largest palindrome product"
  [m n]
  (apply max
    (let [numbers (range m n)]
      (filter palindrome? (for [x numbers
                                y numbers]
                            (* x y)) ))))

(defn problem4b
  "Largest palindrome product"
  [m n]
  (apply max
    (let [numbers (range m n)]
      (for [x numbers
            y numbers
            :let [product (* x y)]
            :when (palindrome? product)]
        product))))


; Problem 5 : Smallest multiple
(defn problem5
  "Smallest multiple"
  [m n]
  (reduce math/lcm (range m n)))


; Problem 6 : Sum square difference
(defn problem6
  "Sum square difference"
  [m n]
  (let [numbers (range m n)]
    (-
      (math/expt (apply + numbers) 2)
      (apply + (map #(math/expt % 2) numbers)))))


; Problem 7 : 10001st prime
(defn problem7
  "10001st prime"
  [n]
  (nth (prime-sieve) (- n 1)))


; Problem 8 : Largest product in a series
(defn problem8
  "Largest product in a series"
  [file]
  (let [numbers (map #(Integer. %) (re-seq #"\d" (slurp file)))]
    (apply max (map #(apply * %) (partition 5 1 numbers)))))


; Problem 9 : Special Pythagorean triplet
(defn problem9
  "Special Pythagorean triplet"
  [n]
  (apply *
    (first
      (for [a (range 1 (int (/ n 2)))
            b (range (+ a 1) n)       ; (a < b < c)
            :let [c (math/sqrt (+ (* a a) (* b b)))]
            :when (= n (+ a b c))]
        [a b c]))))


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
(defn- list-adder [l]
  (loop [l (reverse l)
         acc ()
         carry 0]
    (if (empty? l)
      (if (> carry 0)
        (conj acc carry)
        acc)
      (let [n (+ (first l) carry)]
        (recur (rest l) (conj acc (rem n 10)) (quot n 10))))))

(defn problem13
  "Large sum"
  [n file]
  (let [numbers (partition n (map #(Integer. %) (re-seq #"\d" (slurp file))))]
    (loop [numbers numbers
           acc ()]
      (if (empty? numbers)
        acc
        (if (empty? acc)
          (recur (rest numbers) (first numbers))
          (let [n (pad (first numbers) (- (count acc) (count (first numbers))))]
            (recur (rest numbers) (list-adder (map + acc n)))))))))


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
