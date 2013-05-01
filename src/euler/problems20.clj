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