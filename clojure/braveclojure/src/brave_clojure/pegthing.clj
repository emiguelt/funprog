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

(defn trinagular?
  "Is hte number triangular? e.g. 1,3,6,10,15, etc"
  [n]
  (= n (last (take-while #(>= n %) tri))))

(defn row-tri
  "The riangular number at the end of the row n"
  [n]
  (last (take n tri)))

(defn row-num
  "Returns row number the position belongs to: post 1 in row 1, 
  position 2 and 3 in row 2,etc"
  [pos]
  (inc (count (take-while #(> pos %) tri))))
