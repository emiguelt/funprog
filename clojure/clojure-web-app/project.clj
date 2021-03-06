(defproject clojure-web-app "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [com.stuartsierra/component "0.3.0"]
                 [compojure "1.4.0"]
                 [duct "0.5.8"]
                 [environ "1.0.2"]
                 [meta-merge "0.1.1"]
                 [ring "1.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [ring-jetty-component "0.3.0"]
                 [ring/ring-json "0.3.1"]
                 [cheshire "5.1.1"]
                 [org.danielsz/system "0.1.9"]
                 [com.novemberain/monger "2.0.0"]
                 [org.clojure/clojurescript "1.7.48"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [cljs-http "0.1.37"]
                 [org.omcljs/om "0.9.0"]
                 [prismatic/om-tools "0.3.12"]]
  :plugins [[lein-environ "1.0.2"]
            [lein-gen "0.2.2"]
            [lein-figwheel "0.3.9"]]
  :generators [[duct/generators "0.5.8"]]
  :duct {:ns-prefix clojure-web-app}
  :main ^:skip-aot clojure-web-app.main
  :target-path "target/%s/"
  :aliases {"gen"   ["generate"]
            "setup" ["do" ["generate" "locals"]]}
  :profiles
  {:dev  [:project/dev  :profiles/dev]
   :test [:project/test :profiles/test]
   :uberjar {:aot :all}
   :profiles/dev  {}
   :profiles/test {}
   :project/dev   {:dependencies [[reloaded.repl "0.2.1"]
                                  [org.clojure/tools.namespace "0.2.11"]
                                  [org.clojure/tools.nrepl "0.2.12"]
                                  [eftest "0.1.0"]
                                  [kerodon "0.7.0"]]
                   :source-paths ["dev"]
                   :repl-options {:init-ns user}
                   :env {:port 3000}}
   :project/test  {}}
  :cljsbuild 
  {:builds [{:id "dev"
             :source-paths ["src-cljs"] :figwheel true :compiler {:main "clojure-web-app.core"
                        :asset-path "js/out"
                        :output-to "resources/public/js/clojure-web-app.js"
                        :output-dir "resources/public/js/out"}}]}
  )
