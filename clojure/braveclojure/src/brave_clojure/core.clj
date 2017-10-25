(ns brave-clojure.core
  (:use brave-clojure.functions)
  )

(defn say-hello [] (println "hello"))

(def commands {"hello" say-hello})

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [cmd-name (first args)
        cmd (get commands cmd-name)]
    (if (not (nil? cmd))
      (do
        (println "Executing " cmd-name)
        (cmd)
        )
      (println "Nothing to execute"))))
