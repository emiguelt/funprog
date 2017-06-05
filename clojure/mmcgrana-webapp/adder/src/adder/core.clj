(ns adder.core 
  (:use 
    compojure.core
    hiccup.core
    hiccup.page
    ring.middleware.file
    ring.middleware.file-info
    ring.middleware.reload
    ring.middleware.stacktrace
    ring.util.response
    adder.middleware)
  (:require
    [compojure.handler :as handler]
    [ring.adapter.jetty :as jetty]))

(defn view-layout [& content]
  (html5 {:ng-app "adderApp" :lang "en"}
    [:head
       [:meta {:http-equiv "Content-type"
               :content "text/html; charset=utf-8"}]
       [:title "adder"]
       [:link {:href "/adder.css" :rel "stylesheed" :type "text/css"}]]
    [:body content]))

(defn view-input [& [a b]]
  (view-layout
    [:h2 "add two numbers"]
    [:form {:method "post" :action "/"}
     (if (and a b)
       [:p "those are not both numbers!"])
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

(def production?
  (= "production" (get (System/getenv) "APP_ENV")))

(def development?
     (not production?))

(defroutes handler
  (GET "/" [] (view-input))
  (POST "/" [a b]
    (try
      (let [[a b] (parse-input a b)
            sum (+ a b)]
        (view-output a b sum))
      (catch NumberFormatException e
        (view-input a b))))
  (ANY "/*" [path] (redirect "/")))

(def app (-> #'handler
             (wrap-file "public")
             (wrap-file-info)
             (wrap-request-logging)
             (wrap-if development? wrap-reload)
             (wrap-bounce-favicon)
             (wrap-exception-logging)
             (handler/site)
             (wrap-if production? wrap-failsafe)
             (wrap-if development? wrap-stacktrace)))

(defn -main []
  (let [port (Integer/parseInt (or (System/getenv "PORT") "8080"))]
    (jetty/run-jetty app {:port port :join? false})))
