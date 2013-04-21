(ns euler.util
  (:use [clojure.string :as str :only []]))

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

(defn prime? [n]
  (if (and (not= n 2) (even? n)) false
      (some #(= n %) (take-while #(<= % n) (prime-sieve))))) ; in prime-sieve list?

; FIXME optimize?
(defn palindrome? [s]
  (= (str s) (str/reverse (str s))))

(defn pluck
  "Pluck multiple indexes out of a list"
  [s indexes]
  (for [i indexes]
    (nth s i)))

(defn triangle-nums
  "Adds the natural numbers to get a triangle number"
  ([] (triangle-nums 1 1))
  ([i n] (cons n (lazy-seq (triangle-nums (inc i) (+ n (+ i 1)))))))

