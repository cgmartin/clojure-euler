(ns euler.problems20-test
  (:use clojure.test
        euler.problems20))

(deftest problem20-test
  (testing "Sum of multiples 3 and 5"
    (is (= 27     (problem20 10)))
    (is (= 27 (problem20 100)))
  ))

