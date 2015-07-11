
(defproject multiplication-table-of-prime "0.1.0-SNAPSHOT"
  :description "This provides multiplication table of prime numbers"
  :url ""
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 ;; [utils-yushen "0.1.0-SNAPSHOT"]
                 ]
  :main ^:skip-aot multiplication-table-of-prime.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev {:dependencies [[midje "1.6.3"]]}})
