(ns clojure-web-app.endpoint.example
  (:require [compojure.core :refer :all]
            [ring.util.response :refer [response]]))

(defn example-endpoint [config]
  (context "/example" []
    (GET "/" []
      "This is an example endpoint"))
  (routes
    (GET "/hello" [] (response {:hello "world"}))))
