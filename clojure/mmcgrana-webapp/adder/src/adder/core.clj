(ns adder.core 
  (:use 
    compojure.core
    hiccup.core
    hiccup.page)
  (:require
    [compojure.handler :as handler]
    [ring.adapter.jetty :as jetty]))

(defn view-layout [& content]
  (html5 {:ng-app "adderApp" :lang "en"}
    [:head
       [:meta {:http-equiv "Content-type"
               :content "text/html; charset=utf-8"}]
       [:title "adder"]
      :body content]))

(defn view-input []
  (view-layout
    [:h2 "add two numbers"]
    [:form {:method "post" :action "/"}
     [:input.math {:type "text" :name "a"}][:span.math "+"]
     [:input.math {:type "text" :name "b"}][:br]
     [:input.action {:type "submit" :value "add"}]]))

(defn view-output [a b s]
  (view-layout
    [:h2 "two numbers added"]
    [:p.math a " + " b " = " s]
    [:a.action {:href "/"} "Add more numbers"]))

(defn parse-input [a b]
  [(Integer/parseInt a) (Integer/parseInt b)])

(defroutes theroutes
  (GET "/" [] (view-input))
  (POST "/" [a b]
    (let [[a b] (parse-input a b)
          sum (+ a b)]
      (view-output a b sum))))

(def app (handler/site theroutes))

(defn -main []
  (let [port (Integer/parseInt (or (System/getenv "PORT") "8080"))]
    (jetty/run-jetty app {:port port :join? false})))
