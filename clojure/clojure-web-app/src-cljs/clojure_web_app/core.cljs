(ns ^:figwheel-always clojure-web-app.core
  (:require [cljs-http.client :as http]
            [cljs.core.async :refer [<! >! chan]]
            [om.core :as om]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]))
(enable-console-print!)

(defn get-contacts []
  (go
    (let [response (<! (http/get "/contacts"))]
      (:body response))))

(defcomponent contact-comp [contact _]
  (render [_]
    (dom/li (str (:firstname contact) " " (:lastname contact)))))

(defcomponent app [data _]
  (will-mount [_]
    (go
      (let [contacts (<! (get-contacts))]
        (om/update! data :contacts contacts))))
  (render [_]
    (dom/div
      (dom/h2 (:message data))
      (dom/ul
        (om/build-all contact-comp (:contacts data))))))
