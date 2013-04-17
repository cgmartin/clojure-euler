(ns euler.problems
  (:use euler.util))

;
; Problem 1 : Multiples of 3 and 5
;
; If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9.
; The sum of these multiples is 23.
;
; Find the sum of all the multiples of 3 or 5 below 1000.
;
(defn multiple-3-5? [n]
  (or (multiple? n 3) (multiple? n 5)))

(defn p1
  "Sum of multiples 3 and 5"
  [n]
  (reduce + (filter multiple-3-5? (range n))))

;
; Problem 2 : Even Fibonacci numbers
;
; Each new term in the Fibonacci sequence is generated by adding the previous two
; terms. By starting with 1 and 2, the first 10 terms will be:
;
; 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
;
; By considering the terms in the Fibonacci sequence whose values do not
; exceed four million, find the sum of the even-valued terms.
;
(defn p2
  "Even Fibonacci numbers"
  [m]
  (reduce +
    (filter even?
      (take-while #(<= % m) (fib 1 2)))))



