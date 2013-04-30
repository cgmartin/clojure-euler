(ns euler.problems20-test
  (:use clojure.test
        euler.problems20))

(deftest problem20-test
  (testing "Factorial digit sum"
    (is (= 27  (problem20 10)))
    (is (= 648 (problem20 100)))
  ))

(deftest problem21-test
  (testing "Amicable numbers"
    (is (= 504   (problem21 1000)))
    (is (= 31626 (problem21 10000)))
    ))

