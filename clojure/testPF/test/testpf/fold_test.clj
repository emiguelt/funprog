(ns testpf.fold-test
  (:use midje.sweet)
  (:use [testpf.fold]))

(facts "Left folding"
  (fact "returns initial element when empty list"
    (fold-left 1 nil nil)  => 1)
  (fact "return sum of element of list"
    (fold-left 0 '(1 2 3) (fn [x y] (+ x y) )) => 6)
 )
