(ns euler.problems30-test
  (:use clojure.test
        euler.problems30))

(deftest problem30-test
  (testing "Digit fifth powers"
    (is (= 1301  (problem30 3)))
    (is (= 19316 (problem30 4)))
  ))

(deftest problem31-test
  (testing "Coin sums"
    (is (= 68  (problem31 '(200 100 50 20 10 5 2 1) 25)))
    (is (= 451 (problem31 '(200 100 50 20 10 5 2 1) 50)))
    ))

(deftest problem32-test
  (testing "Pandigital products"
    (is (= 52  (problem32 (range 1 6))))
    ))

(deftest problem33-test
  (testing "Digit canceling fractions"
    (is (= 100  (problem33)))
    ))
