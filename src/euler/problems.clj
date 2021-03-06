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
