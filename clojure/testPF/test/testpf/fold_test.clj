(ns testpf.fold-test
  (:use midje.sweet)
  (:use [testpf.fold]))

(facts "Left folding"
       (fact "returns initial element when empty list"
             (fold-left 1 nil nil)  => 1)
       )