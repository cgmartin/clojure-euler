(ns core
  (:gen-class)
  (:use euler.problems
        euler.problems10
        euler.problems20))

(defn -main
  "For running tests in debugger"
  [& args]
  (println (problem26 100)))

(-main)
