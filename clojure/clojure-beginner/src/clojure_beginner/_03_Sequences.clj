(ns clojure-beginner._03_Sequences)

;; Create a sequence
(def someSeq (seq ["a" "b" "c"]))
(println someSeq)

;; Sequence constructor
(println (cons "xyz" someSeq))
(println (cons someSeq "xyz"))                              ;; second parameter is a sequence

;; Conj
(println (conj someSeq "xyz"))

;; Sequence operations
(println (concat someSeq someSeq))

(println (distinct (concat someSeq someSeq)))

(println (reverse someSeq))

(println (sort (concat someSeq someSeq)))

(println (first someSeq))
(println (rest someSeq))
(println (last someSeq))