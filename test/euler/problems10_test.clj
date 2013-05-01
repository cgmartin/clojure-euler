(ns euler.problems10-test
  (:use clojure.test
        euler.problems10))

(deftest problem10-test
  (testing "Summation of primes"
    (is (= 17 (problem10 10)))
    ))

(deftest problem11-test
  (testing "Largest product in a grid"
    (is (= 9603 (problem11 2 20 "./data/problem11.txt")))
    ))

(deftest problem12-test
  (testing "Highly divisible triangular number"
    (is (= 28 (problem12 5)))
    ))

(deftest problem13-test
  (testing "Large sum"
    (is (= "5537376230" (apply str (take 10 (problem13 50 "./data/problem13.txt")))))
    ))

(deftest problem14-test
  (testing "Longest Collatz sequence"
    (is (= 9 (problem14 10)))
    (is (= 97 (problem14 100)))
    ))

(deftest problem15-test
  (testing "Lattice paths"
    (is (= 6 (problem15 2)))
    ))

(deftest problem16-test
  (testing "Power digit sum"
    (is (= 26 (problem16 2 15)))
    ))

(deftest problem17-test
  (testing "Number letter counts"
    (is (= 19 (problem17 1 5)))
    ))

(deftest problem18-test
  (testing "Maximum path sum I"
    (is (= 23 (problem18 "./data/problem18-1.txt")))
    ))

(deftest problem19-test
  (testing "Counting Sundays"
    (is (= 85 (problem19 1901 1950 6)))
    ))
