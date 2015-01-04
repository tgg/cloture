(defproject cloture "0.1.0"
  :description "Clojure tool to look into jar files"
  :url "http://github.com/tgg/cloture"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/tools.cli "0.3.1"]]
  :main ^:skip-aot cloture.core
  :target-path "target/%s"
  :deploy-repositories [["releases" :clojars]]
  :profiles {:uberjar {:aot :all}})
