(defproject mysu.httpkit/chat-websocket "1.0"
  :description "Chat using websocket, example from httpkit"
  :url "http://"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.671"]
                 [ring/ring-core "1.5.1"]
                 [compojure "1.5.2"]
                 [org.clojure/data.json "0.2.6"]
                 [org.clojure/tools.logging "0.4.0"]
                 [ch.qos.logback/logback-classic "1.2.3"]
                 [http-kit "2.2.0"]
                 [javax.servlet/servlet-api "2.5"]
                 ]
  :warn-on-reflection true
  :test-paths ["test"]
  :plugins [[swank-clojure/swank-clojure "1.4.3"]
            [lein-cljsbuild "1.1.6"]]
  :cljsbuild {
              :builds [{:source-paths ["src-cljs"]
                        :compiler {:output-to "static/main.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}]}
  :license {:name "Apache License, Version 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0.html"}
  :main main)
