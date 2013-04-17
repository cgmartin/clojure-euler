(ns euler.util)

(defn multiple? [n div]
  (= 0 (mod n div)))

(defn fib [a b]
  (cons a
    (lazy-seq
      (fib b (+ b a)))))


