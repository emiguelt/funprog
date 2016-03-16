(ns ^:figwheel-always clojure-web-app.core
  (:require [cljs-http.client :as http]
            [cljs.core.async :refer [<! >! chan]]))
(enable-console-print!)

(go
  (let [response (<! (http/get "contacts"))]
    (println (:body response))))

(println "hello from clojure script, figwheel rocks!!")
