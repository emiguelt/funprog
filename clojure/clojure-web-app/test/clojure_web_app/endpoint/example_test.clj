(ns clojure-web-app.endpoint.example-test
  (:require [com.stuartsierra.component :as component]
            [clojure.test :refer :all]
            [kerodon.core :refer :all]
            [kerodon.test :refer :all]
            [clojure-web-app.endpoint.example :as example]))

(def handler
  (example/example-endpoint {}))

(deftest smoke-test
  (testing "example page exists"
    (-> (session handler)
        (visit "/example")
        (has (status? 200) "page exists"))))
