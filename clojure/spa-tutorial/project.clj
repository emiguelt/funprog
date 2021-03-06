(defproject spa-tutorial "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.7.0-beta1"]
                 [org.clojure/clojurescript "0.0-3208"]
                 [org.omcljs/om "0.8.8"]
                 [org.clojure/core.async "0.2.374"]
                 [org.omcljs/om "0.9.0"]
                 [prismatic/om-tools "0.4.0"]
                 [http-kit "2.1.19"]
                 [secretary "1.2.3"]]

  :plugins [[lein-cljsbuild "1.0.5"]]

  :source-paths ["src" "target/classes"]

  :clean-targets ["out/spa_tutorial" "out/spa_tutorial.js"]

  :cljsbuild {
    :builds [{:id "spa-tutorial"
              :source-paths ["src"]
              :compiler {
                :main spa-tutorial.core
                :output-to "out/spa_tutorial.js"
                :output-dir "out"
                :optimizations :none
                :verbose true}}]})
