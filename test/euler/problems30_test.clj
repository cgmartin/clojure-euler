(ns euler.problems30-test
  (:use clojure.test
        euler.problems30))

(deftest problem30-test
  (testing "Digit fifth powers"
    (is (= 1301  (problem30 3)))
    (is (= 19316 (problem30 4)))
  ))
