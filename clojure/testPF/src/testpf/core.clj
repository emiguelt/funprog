(ns testpf.core
  (:require [clojure.string :refer :all]))

(defn checkparenthesis [strg]
 (defn getvalue [c]
    (case c
      "(" 1
      ")" -1
      0))   
 (defn checkpar [acum strg]
   (cond
     (< acum 0 ) false
     (blank? strg) (= 0 acum)
     :else (checkpar 
             (+ (getvalue (str (get strg 0))) acum) 
             (subs strg 1))))
 (checkpar 0 strg)
)