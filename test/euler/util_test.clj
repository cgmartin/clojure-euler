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

(deftest prime-factors-test
  (testing
    (is (= '(2 2 7) (prime-factors 28)))
    ))

(deftest palindrome?-test
  (testing
    (is (palindrome? 1))
    (is (palindrome? 22))
    (is (palindrome? 303))
    (is (palindrome? 4114))
    (is (palindrome? 12321))
    (is (palindrome? "abba"))
    (is (palindrome? "anana"))
    (is (not (palindrome? 12)))
    (is (not (palindrome? 123)))
    (is (not (palindrome? 1232)))
    ))

(deftest pluck-test
  (testing
    (is (= '(3 4) (pluck '(1 2 3 4 5) '(2 3))))
    (is (= '(3 5) (pluck '(1 2 3 4 5) '(2 4))))
    ))

(deftest digits-test
  (testing
    (is (= '(0)     (digits 0)))
    (is (= '(3 4)   (digits 34)))
    (is (= '(1 2 3) (digits 123)))
    ))

(deftest list-multiply-digit-test
  (testing
    (is (= "71874699" (reduce str (list-multiply-digit '(2 3 9 5 8 2 3 3) 3))))
    ))

(deftest list-multiply-test
  (testing
    (is (= "139676498390" (reduce str (list-multiply '(2 3 9 5 8 2 3 3) '(5 8 3 0)))))
    ))

(deftest sum-proper-divisors-test
  (testing
    (is (= 284 (sum-proper-divisors 220)))
    (is (= 220 (sum-proper-divisors 284)))
    (is (= 568 (sum-proper-divisors 300)))
    ))

(deftest abundant-number?-test
  (testing
    (is (= true  (abundant-number? 12)))
    (is (= false (abundant-number? 15)))
    (is (= true  (abundant-number? 102)))
    ))