(ns euler.problems20-test
  (:use clojure.test
        euler.problems20))

(deftest problem20-test
  (testing "Factorial digit sum"
    (is (= 27  (problem20 10)))
  ))

(deftest problem21-test
  (testing "Amicable numbers"
    (is (= 504   (problem21 1000)))
    ))

(deftest problem22-test
  (testing "Names scores"
    (is (= 871198282   (problem22 "./data/names.txt")))
    ))

(deftest problem23-test
  (testing "Non-abundant sums"
    (is (= 2867    (problem23 103)))
    ))

(deftest problem24-test
  (testing "Lexicographic permutations"
    (is (= "102" (problem24 '(0 1 2) 3)))
    ))

(deftest problem25-test
  (testing "Lexicographic permutations"
    (is (= 45  (problem25 10)))
    (is (= 476 (problem25 100)))
    ))

(deftest problem26-test
  (testing "Reciprocal cycles"
    (is (= 19  (problem26 20)))
    (is (= 97  (problem26 100)))
    ))

(deftest problem27-test
  (testing "Quadratic primes"
    (is (= -21   (problem27 10)))
    (is (= -1455 (problem27 100)))
    ))

(deftest problem28-test
  (testing "Number spiral diagonals"
    (is (= 101 (problem28 5)))
    (is (= 261 (problem28 7)))
    ))
