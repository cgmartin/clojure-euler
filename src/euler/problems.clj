(ns euler.problems
  (:use euler.util))

;
; Problem 1 : Multiples of 3 and 5
; http://projecteuler.net/problem=1
;
(defn multiple-3-5? [n]
  (or (multiple? n 3) (multiple? n 5)))

(defn p1
  "Sum of multiples 3 and 5"
  [n]
  (reduce + (filter multiple-3-5? (range n))))

;
; Problem 2 : Even Fibonacci numbers
; http://projecteuler.net/problem=2
;
(defn p2
  "Even Fibonacci numbers"
  [m]
  (reduce +
    (filter even?
      (take-while #(<= % m) (fib 1 2)))))



