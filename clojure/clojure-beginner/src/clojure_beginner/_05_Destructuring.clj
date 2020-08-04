(ns clojure-beginner._05_Destructuring)

;; Split complex data type in simple ones

(def aSeq (seq [1 2 3 4 5]))
(let [[a b c] aSeq] (println "First 3 elements" a b c))
(let [[a & rest] aSeq] (println "First element " a "& the rest elements: " rest))

(defstruct animal :name :kind)
(def someAnimal (struct animal "Max" "cat"))
(let [{animalName :name} someAnimal] (println "Animal name:" animalName))

(def someMap {'key "123" 'value "xyz"})
(let [{entry 'key other 'bla} someMap] (println "Print known and unknown entries" entry other))
