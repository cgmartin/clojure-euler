(ns euler.util
  (:use [clojure.string :as str :only []])
  (:require [clojure.math.numeric-tower :as math]))

; REPL
; (require '[clojure.math.numeric-tower :as math])
; (require '[clojure.math.combinatorics :as combo])

; From http://stackoverflow.com/questions/2352020/debugging-in-clojure
(defmacro dbg[x]
  `(let [x# ~x] (println "dbg:" '~x "=" x#) x#))

(defn multiple? [n div]
  (zero? (mod n div)))

; From http://clojuredocs.org/clojure_core/clojure.core/lazy-seq#example_772
(defn fib [a b]
  (cons a
    (lazy-seq
      (fib b (+ b a)))))

; From http://clj-me.cgrand.net/2009/07/30/everybody-loves-the-sieve-of-eratosthenes/
(defn prime-sieve []
  (letfn [(enqueue [sieve n step]
            (let [m (+ n step)]
              (if (sieve m)
                (recur sieve m step)
                (assoc sieve m step))))
          (next-sieve [sieve candidate]
            (if-let [step (sieve candidate)]
              (-> sieve
                (dissoc candidate)
                (enqueue candidate step))
              (enqueue sieve candidate (+ candidate candidate))))
          (next-primes [sieve candidate]
            (if (sieve candidate)
              (recur (next-sieve sieve candidate) (+ candidate 2))
              (cons candidate
                (lazy-seq (next-primes (next-sieve sieve candidate)
                            (+ candidate 2))))))]
    (cons 2 (lazy-seq (next-primes {} 3)))))

(def prime?
  (memoize
    (fn [n]
      (if (and (not= n 2) (even? n)) false
        (some #(= n %) (take-while #(<= % n) (prime-sieve))))))) ; in prime-sieve list?

; From http://stackoverflow.com/questions/9556393/clojure-tail-recursion-with-prime-factors
(defn prime-factors
  ([n]
    (prime-factors n 2 '()))
  ([n candidate acc]
    (cond (<= n 1) (reverse acc)
      (multiple? n candidate) (recur (/ n candidate) candidate (cons candidate acc))
      :else (recur n (inc candidate) acc))))

; http://en.wikipedia.org/wiki/Divisor
(defn num-divisors [n]
  (reduce #(* %1 (inc %2)) 1
    (vals
      (frequencies (prime-factors n)))))

; http://en.wikipedia.org/wiki/Divisor_function
(defn sum-int-divisors [n]
  (reduce *
    (map
      (fn [m]
        (reduce +
          (map #(math/expt (first m) %) (range (inc (last m))))))
      (frequencies (prime-factors n)))))

(defn sum-proper-divisors [n]
  (- (sum-int-divisors n) n))

(defn abundant-number? [n]
  (> (sum-proper-divisors n) n))

; https://en.wikipedia.org/wiki/Order_(number_theory)
(defn multiplicative-order [a n]
  (loop [k 1]
    (if (= (mod (math/expt a k) n) 1) k
      (if (= k n) nil
        (recur (inc k))))))

; FIXME optimize?
(defn palindrome? [s]
  (= (str s) (str/reverse (str s))))

(defn pluck
  "Pluck multiple indexes out of a list"
  [s indexes]
  (for [i indexes]
    (nth s i)))

(defn pad
  "Pad a list with items"
  ([l n] (pad l n 0))
  ([l n p]
    (if (> n 0)
      (concat (take n (repeat p)) l)
      l)))

(defn triangle-nums
  "Adds the natural numbers to get a triangle number"
  ([] (triangle-nums 1 1))
  ([i n] (cons n (lazy-seq (triangle-nums (inc i) (+ n (+ i 1)))))))

(defn factorial
  [n]
  (reduce * (range 1 (inc n))))

(defn quadratic
  "Quadratic function"
  [x a b c]
  (+ (* a (math/expt x 2)) (* b x) c))

(defn digits
  "Get the digits of a number"
  [n]
  (map #(Integer/parseInt (str %)) (seq (str n))))

(defn- next-list-digit
  [s]
  (if (empty? s) 0 (first s)))

(defn list-add
  "Add two (long) lists of digits"
  [a b]
  (loop [a     (reverse a)
         b     (reverse b)
         acc   ()
         carry 0]
    (let [v (+ (next-list-digit a) (next-list-digit b) carry)
          d (rem v 10)
          c (quot v 10)]
      (if (and (empty? a) (empty? b))
        (if (zero? carry) acc (conj acc carry))
        (recur (rest a) (rest b) (conj acc d) c)))))

(defn list-add-fib [a b]
  (cons a
    (lazy-seq
      (list-add-fib b (list-add b a)))))

(defn list-multiply-digit
  "Multiply a digit against a (long) list of digits"
  [a n]
  (loop [a (reverse a)
         n n
         acc ()
         carry 0]
    (let [v (+ (* (next-list-digit a) n) carry)
          d (rem v 10)
          c (quot v 10)]
      (if (empty? a)
        (if (zero? carry) acc (conj acc carry))
        (recur (rest a) n (conj acc d) c)))))

(defn list-multiply
  "Multiply two (long) lists of digits"
  [a b]
  (loop [a   a
         b   (reverse b)
         acc ()
         i   0]
    (if (empty? b)
      (reduce list-add acc)
      (recur
        a                          ; 23958233
        (rest b)                   ;   5(8)31 x  = 71874699 (00)
        (conj acc (concat (list-multiply-digit a (first b)) (repeat i 0)))
        (inc i)))))

