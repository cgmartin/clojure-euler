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

(deftest problem8-test
  (testing "Largest product in a series"
    (is (= 40824 (problem8 "./data/problem8.txt")))
    ))

(deftest problem9-test
  (testing "Special Pythagorean triplet"
    (is (= 60       (problem9 12)))
    (is (= 31875000 (problem9 1000)))
    ))

(deftest problem10-test
  (testing "Summation of primes"
    (is (= 17           (problem10 10)))
    (is (= 142913828922 (problem10 2000000)))
    ))

(deftest problem11-test
  (testing "Largest product in a grid"
    (is (= 9603     (problem11 2 20 "./data/problem11.txt")))
    (is (= 70600674 (problem11 4 20 "./data/problem11.txt")))
    ))

(deftest problem12-test
  (testing "Highly divisible triangular number"
    (is (= 28       (problem12 5)))
    (is (= 76576500 (problem12 500)))
    ))

(deftest problem13-test
  (testing "Large sum"
    (is (= "5537376230" (apply str (take 10 (problem13 50 "./data/problem13.txt")))))
    ))