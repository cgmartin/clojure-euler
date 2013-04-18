(ns euler.problems-test
  (:use clojure.test
        euler.problems))

(deftest p1-test
  (testing "Sum of multiples 3 and 5"
    (is (= 23     (p1 10)))
    (is (= 233168 (p1 1000)))
  ))

(deftest p2-test
  (testing "Even Fibonacci numbers"
    (is (= 10      (p2 10)))
    (is (= 4613732 (p2 4000000)))
  ))

(deftest p3-test
  (testing "Largest prime factor"
    (is (= 29   (p3 13195)))
    (is (= 6857 (p3 600851475143)))
  ))

(deftest p4-test
  (testing "Largest palindrome product"
    (is (= 9009     (p4 10   100)))
    (is (= 906609   (p4 100  1000)))
    ;(is (= 99000099 (p4 1000 10000))) ; FIXME slow, needs optimization
    ))
