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

