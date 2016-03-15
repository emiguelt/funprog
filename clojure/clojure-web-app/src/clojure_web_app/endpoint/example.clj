(ns clojure-web-app.endpoint.example
  (:require [compojure.core :refer :all]
            [ring.util.response :refer [response]]
            [clojure.java.io :as io]
            [clojure-web-app.component.repo :as r]))

(defn example-endpoint [{repo :contact-repo}]
  (context "/example" []
    (GET "/" []
      "This is an example endpoint"))
  (routes
    (GET "/hello" [] (response {:hello "world"}))
    (GET "/" [] (io/resource "public/index.html"))
    (GET "/contacts" [] (response (r/find-all repo)))))
