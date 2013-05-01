(ns euler.problems-test
  (:use clojure.test
        euler.problems))

(deftest problem1-test
  (testing "Sum of multiples 3 and 5"
    (is (= 23 (problem1 10)))
  ))

(deftest problem2-test
  (testing "Even Fibonacci numbers"
    (is (= 10 (problem2 10)))
  ))

(deftest problem3-test
  (testing "Largest prime factor"
    (is (= 29 (problem3 13195)))
  ))

(deftest problem4-test
  (testing "Largest palindrome product"
    (is (= 9    (problem4 1    10)   (problem4b 1    10)))
    (is (= 9009 (problem4 10   100)  (problem4b 10   100)))
    ))

(deftest problem5-test
  (testing "Smallest multiple"
    (is (= 2520 (problem5 1 10)))
    ))

(deftest problem6-test
  (testing "Sum square difference"
    (is (= 2640 (problem6 1 11)))
    ))

(deftest problem7-test
  (testing "10001st prime"
    (is (= 13 (problem7 6)))
    ))

(deftest problem8-test
  (testing "Largest product in a series"
    (is (= 40824 (problem8 "./data/problem8.txt")))
    ))

(deftest problem9-test
  (testing "Special Pythagorean triplet"
    (is (= 60 (problem9 12)))
    ))
