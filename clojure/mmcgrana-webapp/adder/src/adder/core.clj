(ns adder.core (:use compojure.core)
  (:use hiccup.core)
  (:use hiccup.page-helpers))

(defn view-layout [& content]
  (html
    (doctype :xhtml-strict)
    (xhtml-tag "en"
      [:head
       [:meta {:http-equiv "Content-type"
               :content "text/html; charset=utf-8"}]
       [title: "adder"]
      :body content])))

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
    [:p.math a " + " b " = " sum]
    [:a.action {:href "/"} "Add more numbers"]))

(defn parse-input [a b]
  [(Integer/parseInt a) (Integer/parseInt b)])

(defroutes app
  (GET "/" [] (view-input))
  (POST "/" [a b]
    (let [[a b] (parse-input a b)
          sum (+ a b)]
      (view-output a b sum))))
