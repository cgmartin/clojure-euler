(ns euler.problems30
  (:use euler.util
        clojure.set)
  (:require [clojure.math.numeric-tower :as math])
  (:require [clojure.math.combinatorics :as combo]))

; For details on a particular problem, go to:
; http://projecteuler.net/problem=1

; REPL
; (require '[clojure.math.numeric-tower :as math])
; (require '[clojure.math.combinatorics :as combo])

; Problem 30 : Digit fifth powers
(defn problem30
  "Digit fifth powers"
  [n]
  (let [max-digit (math/expt 9 n)
        limit (* (count (digits max-digit)) max-digit)]
    (reduce +
      (for [x (range 10 limit)
            :when (= x (reduce + (map #(math/expt % n) (digits x))))]
        x))))


; Problem 31 : Coin sums
(defn problem31
  "Coin sums"
  [coins amount]
  (num-combo-sum-fit coins amount))


; Problem 32 : Pandigital products
(defn- pandig-prod?
  [a b s]
  (let [d (concat (digits a) (digits b) (digits (* a b)))]
    (= s (sort d))))

(defn problem32
  "Pandigital products"
  [s]
  (let [half-digits (quot (count s) 2)]
    (reduce +
      (distinct
        ; given a 9 digit product of A*B, and a number A of length 1<a<5,
        ; the other number B must have length 4.5-A < B < 5-A
        (for [a (range 1 (math/expt 10 half-digits))
              b (range 1 (math/expt 10 (- (inc half-digits) (num-digits a))))
              :when (pandig-prod? a b s)]
          (* a b))))))


; Problem 33 : Digit canceling fractions
(defn- cancelled-frac
  [n d]
  (let [digits-n (digits n)
        digits-d (digits d)
        cancel-n (difference (set digits-n) (set digits-d))
        cancel-d (difference (set digits-d) (set digits-n))]
    (if (and (not= (first digits-n) (last digits-n))
             (not= (first digits-d) (last digits-d))
             (not= 0 (last digits-n) (last digits-d))
             (= 1 (count cancel-n) (count cancel-d))
             (> (first cancel-d) (first cancel-n)))
      (/ (first cancel-n) (first cancel-d)))))

(defn problem33
  "Digit canceling fractions"
  []
  (denominator
    (reduce *
      (for [d (range 11 100)
            n (range 10 d)
            :let [f (/ n d)
                  c (cancelled-frac n d)]
            :when (= c f)]
        f))))
