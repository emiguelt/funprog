(ns brave-clojure.core-functions-test
  (:require [clojure.test :refer :all]))

(def sum #(reduce + %))
(def avg #(/ (sum % ) (count %)))
(defn stats [numbers]
  (map #(% numbers) [sum count avg]))

(deftest map-test
  (testing "map for two sequences"
    (is (= '(2 4 6) (map (fn [a b] (+ a b)) '(1 2 3) '(1 2 3)))))
  
  (testing "map receiving sequence of functions"
    (is (= '(6 3 2) (stats [1 2 3]))))

  (testing "map for getting Map values"
    (def data [{:a 1} {:a 2} {:a 3}])
    (is (= '(1 2 3) (map :a data)))) 
)
