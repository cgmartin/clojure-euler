(ns euler.problems20-test
  (:use clojure.test
        euler.problems20))

(deftest problem20-test
  (testing "Factorial digit sum"
    (is (= 27  (problem20 10)))
    (is (= 648 (problem20 100)))
  ))

