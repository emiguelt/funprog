(ns pegthing.core
  (require [clojure.set :as set])
  (:gen-class))

(declare successful-move prompt-move game-over prompt-rows)

;;;;
;; Create the board
;;;;
(defn tri* "Generates lazy sequence of triangular numbers"
  ([] (tri* 0 1))
  ([sum n]
   (let [new-sum (+ sum n)]
     (cons new-sum (lazy-seq (try* new-sum (inc n)))))))

(def tri (tri*))


