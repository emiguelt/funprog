(ns testpf.core
  (:require [clojure.string :refer :all]))

(defn checkparenthesis [strg]
 (defn getvalue [c]
    (case c
      "(" 1
      ")" -1
      0))   
 (defn checkpar [acum strg]
   (if (blank? strg)
     (= 0 acum)
     (checkpar 
       (+ (getvalue (str (get strg 0))) acum) 
       (subs strg 1))))
 (checkpar 0 strg)
)