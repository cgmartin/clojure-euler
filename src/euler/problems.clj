(ns euler.problems
  (:use euler.util)
  (:require [clojure.math.numeric-tower :as math])
  (:require [clojure.math.combinatorics :as combo]))

; REPL
; (require '[clojure.math.numeric-tower :as math])
; (require '[clojure.math.combinatorics :as combo])

;
; Problem 1 : Multiples of 3 and 5
; http://projecteuler.net/problem=1
;
(letfn [(multiple-3-5? [n]
          (or (multiple? n 3) (multiple? n 5)))]
  (defn problem1
    "Sum of multiples 3 and 5"
    [n]
    (reduce + (filter multiple-3-5? (range n)))))

;
; Problem 2 : Even Fibonacci numbers
; http://projecteuler.net/problem=2
;
(defn problem2
  "Even Fibonacci numbers"
  [n]
  (reduce +
    (filter even?
      (take-while #(<= % n) (fib 1 2)))))

;
; Problem 3 : Largest prime factor
; http://projecteuler.net/problem=3
; http://en.wikipedia.org/wiki/Trial_division
;
(defn problem3
  "Largest prime factor"
  [n]
  (last
    (filter #(multiple? n %)
            (take-while #(<= (* % %) n) (prime-sieve)))))

;
; Problem 4 : Largest palindrome product
; http://projecteuler.net/problem=4
; http://clojuredocs.org/clojure_core/clojure.core/for
;
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

;
; Problem 5 : Smallest multiple
; http://projecteuler.net/problem=5
;
(defn problem5
  "Smallest multiple"
  [m n]
  (reduce math/lcm (range m n)))

;
; Problem 6 : Sum square difference
; http://projecteuler.net/problem=6
;
(defn problem6
  "Sum square difference"
  [m n]
  (let [numbers (range m n)]
    (-
      (math/expt (apply + numbers) 2)
      (apply + (map #(math/expt % 2) numbers)))))

;
; Problem 7 : 10001st prime
; http://projecteuler.net/problem=7
;
(defn problem7
  "10001st prime"
  [n]
  (nth (prime-sieve) (- n 1)))

;
; Problem 8 : Largest product in a series
; http://projecteuler.net/problem=8
;
(defn problem8
  "Largest product in a series"
  [file]
  (let [numbers (map #(Integer. %) (re-seq #"\d" (slurp file)))]
    (apply max (map #(apply * %) (partition 5 1 numbers)))))

;
; Problem 9 : Special Pythagorean triplet
; http://projecteuler.net/problem=9
;
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

;
; Problem 10 : Summation of primes
; http://projecteuler.net/problem=10
;
(defn problem10
  "Summation of primes"
  [n]
  (reduce +
    (take-while #(< % n) (prime-sieve))))

;
; Problem 11 : Largest product in a series
; http://projecteuler.net/problem=8
;
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
