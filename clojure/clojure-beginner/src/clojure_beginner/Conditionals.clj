(ns clojure-beginner.Conditionals)

;; Basic IF
(defn simple-if [x]
  (println "simple-if")
  (if (= x 2)
    (println "Equal to 2")
    (println "Not equal to 2")))

(simple-if 2)
(simple-if 3)

;; IF with multiple lines and composed condition
(defn if-do [x]
  (println "if-do")
  (if (or (= x 2) (= x 4))
    (do
      (println "Equal to 2")
      (println "just another command when true, maybe it's 4")
      )
    (println "Not equal to 2 neither 4")))

(if-do 2)
(if-do 3)

;; When
(defn the-when [x]
  (println "the-when")
  (when (= x 2)
    (println "It's two")
    (println "multiple lines when true!")
    (println "not false branch")
    )
  )

(the-when 2)
(the-when 3)

;; Case
(defn the-case [x]
  (println "the-case")
  (try
    (case x
      "test" (println "it is a test")
      1 (println "It is 1")
      )
    (catch IllegalArgumentException e (println "no matches, there was an ex: " (. e getClass))))
  )

(the-case "test")
(the-case 1)
(the-case "bla")

;; Cond
(defn the-cond [x]
  (println "the-cond")
  (cond
    (= "test" x) (println "It's test")
    (= x 1) (println "It's 1")
    :else (println "no matches, no exceptions")
    ))

(the-cond "test")
(the-cond 1)
(the-cond "bla")