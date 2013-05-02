(ns euler.problems20
  (:use euler.util)
  (:require [clojure.math.numeric-tower :as math])
  (:require [clojure.math.combinatorics :as combo]))

; For details on a particular problem, go to:
; http://projecteuler.net/problem=1

; REPL
; (require '[clojure.math.numeric-tower :as math])
; (require '[clojure.math.combinatorics :as combo])

; Problem 20 : Factorial digit sum
(defn problem20
  "Factorial digit sum"
  [n]
  (reduce +
    (reduce list-multiply
      (map digits (range 1 (inc n))))
    ))


; Problem 21 : Amicable numbers
(defn problem21
  "Amicable numbers"
  [n]
  (let [spd-map
        (apply conj
          (map #(hash-map % (sum-proper-divisors %)) (range 2 n)))]
    (reduce +
      (map first
        (filter
          #(and
             (not= (first %) (last %))
             (= (first %) (spd-map (last %))))
          (seq spd-map))))))


; Problem 22 : Names scores
(defn problem22
  "Names scores"
  [file]
  (let [names (sort (map #(last (re-find #"\"(.*)\"" %)) (.split (slurp file) ",")))]
    (reduce +
      (for [i (range (count names))
            :let [j (inc i)]]
        (* j
          (reduce +
            (map #(inc (- (int %) (int \A))) (seq (nth names i)))))))))


; Problem 23 : Non-abundant sums
(defn problem23
  "Non-abundant sums"
  [n]
  (let [ab-sums
        (set (map #(+ (first %) (last %))
               (combo/selections (filter abundant-number? (range 1 n)) 2)))]
    (reduce +
      (filter #(nil? (ab-sums %)) (range 1 n)))))


; Problem 24 : Lexicographic permutations
(defn problem24
  "Lexicographic permutations"
  [s i]
  (apply str
    (nth (combo/permutations s) (dec i))))


; Problem 25 : 1000-digit Fibonacci number
(defn problem25
  "1000-digit Fibonacci number"
  [n]
  (inc
    (first ; zero-based index
      (first ; vector
        (drop-while #(< (count (last %)) n)
          (map-indexed vector
            (list-add-fib [1] [1])))))))


; Problem 26 : Reciprocal cycles
(defn problem26
  "Reciprocal cycles"
  [n]
  (key
    (apply max-key val
      (into {}
        (filter #(not (nil? (val (first %))))
          (map #(hash-map % (multiplicative-order 10 %))
            (take-while #(< % n) (prime-sieve))))))))

; Problem 27 : Quadratic primes
(defn problem27
  "Quadratic primes"
  [r]
  (let [best
        (reduce #(if (> (%1 :n) (%2 :n)) %1 %2)
          (for [a (range (- 1 r) r)
                b (range (- 1 r) r)]
            (loop [a a
                   b b
                   n 0]
              (if (not (prime? (quadratic n 1 a b)))
                {:a a, :b b, :n n}
                (recur a b (inc n))))))]
    (* (best :a) (best :b))))
