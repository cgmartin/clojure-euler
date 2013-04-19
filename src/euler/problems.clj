(ns euler.problems
  (:use euler.util)
  (:require [clojure.math.numeric-tower :as math]))

; REPL
; (require '[clojure.math.numeric-tower :as math])

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
; http://projecteuler.net/problem=6
;
(defn problem7
  "10001st prime"
  [n]
  (nth (prime-sieve) (- n 1)))
