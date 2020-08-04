(ns clojure-beginner._07_Exceptions)

(defn someFun [x]
  (try
    (cond
      (< x 5) (println "No exceptions")
      (< x 10) (throw (RuntimeException. (str "5 <=" x "< 10")))
      :else (throw (Exception. "General exception")))
    (catch RuntimeException e (println "The runtime ex: " (.getMessage e)))
    (catch Exception e (println "The general ex: " (.getMessage e)))
    (finally (println "Print always"))))

(someFun 1)
(someFun 6)
(someFun 11)
