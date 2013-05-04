(ns euler.problems30
  (:use euler.util)
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
(defn ways-to-make-change
  [coins amount]
  (cond
    (or (empty? coins) (< amount 0)) 0
    (zero? amount) 1
    :else (+ (ways-to-make-change (rest coins) amount)
             (ways-to-make-change coins        (- amount (first coins))))))

(defn problem31
  "Coin sums"
  [coins amount]
  (ways-to-make-change coins amount))
