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

(deftest reduce-test
  (testing "Map with reduce"
    (is (= {:a 1 :b 2} (reduce (fn [new-map [key val]] (assoc new-map key (inc val))) 
                               {}
                               {:a 0 :b 1})))
    )
  (testing "Filter a Map with reduce"
    (is (= {:b 2} (reduce (fn [new-map [key val]] (if (even? val) (assoc new-map key val) new-map))
                          {}
                          {:a 1 :b 2})))
    )
  (testing "map using reduce"
    (is (= '(1 2) (reduce (fn [seq_ value] (cons (inc value) seq_))
                          '()
                          (reverse '(0 1)))))
    )
  )
