(ns euler.problems-test
  (:use clojure.test
        euler.problems))

(deftest problem1-test
  (testing "Sum of multiples 3 and 5"
    (is (= 23     (problem1 10)))
    (is (= 233168 (problem1 1000)))
  ))

(deftest problem2-test
  (testing "Even Fibonacci numbers"
    (is (= 10      (problem2 10)))
    (is (= 4613732 (problem2 4000000)))
  ))

(deftest problem3-test
  (testing "Largest prime factor"
    (is (= 29   (problem3 13195)))
    (is (= 6857 (problem3 600851475143)))
  ))

(deftest problem4-test
  (testing "Largest palindrome product"
    (is (= 9        (problem4 1    10)   (problem4b 1    10)))
    (is (= 9009     (problem4 10   100)  (problem4b 10   100)))
    (is (= 906609   (problem4 100  1000) (problem4b 100  1000)))
    ;(is (= 99000099 (problem4 1000 10000))) ; FIXME slow, optimize?
    ))

(deftest problem5-test
  (testing "Smallest multiple"
    (is (= 2520      (problem5 1 10)))
    (is (= 232792560 (problem5 1 20)))
    ))

(deftest problem6-test
  (testing "Sum square difference"
    (is (= 2640      (problem6 1 11)))
    (is (= 25164150  (problem6 1 101)))
    ))

(deftest problem7-test
  (testing "10001st prime"
    (is (= 13     (problem7 6)))
    (is (= 104743 (problem7 10001)))
    ))