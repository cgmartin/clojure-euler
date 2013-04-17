(ns euler.util-test
  (:use clojure.test
        euler.util))

(deftest multiple?-test
  (testing
    (is (multiple? 9 3))
    (is (not (multiple? 9 2)))
    (is (multiple? 8 2))
    (is (not (multiple? 8 3)))
    ))

(deftest fib-test
  (testing
    (is (= '(0 1 1 2 3)   (take 5 (fib 0 1))))
    (is (= '(1 2 3 5 8)   (take 5 (fib 1 2))))
    (is (= '(2 5 7 12 19) (take 5 (fib 2 5))))
    ))

(deftest prime?-test
  (testing
    (is (prime? 6857))
    (is (prime? 2))
    (is (prime? 3))
    (is (not (prime? 4)))
    (is (prime? 71))
    (is (not (prime? 500000)))
    ))

(deftest prime-sieve-test
  (testing
    (is (= '(2 3 5 7 11 13 17 19 23 29) (take 10 (prime-sieve))))
    ))


