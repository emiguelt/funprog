(ns clojure-web-app.system
  (:require [com.stuartsierra.component :as component]
            [duct.component.endpoint :refer [endpoint-component]]
            [duct.component.handler :refer [handler-component]]
            [duct.middleware.not-found :refer [wrap-not-found]]
            [meta-merge.core :refer [meta-merge]]
            [ring.component.jetty :refer [jetty-server]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [clojure-web-app.endpoint.example :refer [example-endpoint]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [clojure-web-app.component.repo :refer [new-contact-repo-component]]
            [system.components.mongo :refer [new-mongo-db]])
  )

(def base-config
  {:app {:middleware [[wrap-not-found :not-found]
                      [wrap-json-body {:keywords? true}]
                      [wrap-json-response]
                      [wrap-defaults :defaults]]
         :not-found  "Resource Not Found"
         :defaults   (meta-merge site-defaults {})}})

(defn new-system [config]
  (let [config (meta-merge base-config config)]
    (-> (component/system-map
         :app  (handler-component (:app config))
         :http (jetty-server (:http config))
         :example (endpoint-component example-endpoint)
         :mongo (new-mongo-db (:mongo-uri config))
         :contact-repo (new-contact-repo-component))
        (component/system-using
         {:http [:app]
          :app  [:example]
          :example [:contact-repo]
          :contact-repo [:mongo]}))))
