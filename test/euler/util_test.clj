(ns euler.util-test
  (:use clojure.test
        euler.util))

(deftest multiple?-test
  (testing
    (is (= true  (multiple? 9 3)))
    (is (= false (multiple? 9 2)))
    (is (= true  (multiple? 8 2)))
    (is (= false (multiple? 8 3)))
  ))

(deftest fib-test
  (testing
    (is (= '(0 1 1 2 3)   (take 5 (fib 0 1))))
    (is (= '(1 2 3 5 8)   (take 5 (fib 1 2))))
    (is (= '(2 5 7 12 19) (take 5 (fib 2 5))))
  ))