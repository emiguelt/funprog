; Defines project and dependencies
(defproject adder "0.0.1"
  :description "Add two numbers."
  :dependencies
  [[org.clojure/clojure "1.8.0"]
   [org.clojure/clojure-contrib "1.2.0"]
   [ring/ring-core "1.5.1"]
   [ring/ring-devel "1.5.1"]
   [ring/ring-jetty-adapter "1.5.1"]
   [compojure "1.5.2"]
   [hiccup/hiccup "1.0.5"]]
  :dev-dependencies
  [[lein-run/lein-run "1.0.0"]]
  :main adder.core)
   
