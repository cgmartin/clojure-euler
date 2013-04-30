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

