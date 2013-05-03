(ns core
  (:gen-class)
  (:use euler.problems
        euler.problems10
        euler.problems20
        euler.problems30))

(defn -main
  "For running tests in debugger"
  [& args]
  (time (println (problem29 2 100))))

(-main)
