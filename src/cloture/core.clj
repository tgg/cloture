(ns cloture.core
  (:gen-class)
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.tools.cli :refer [parse-opts]]
            [cloture.manifest :as mf]))

(def ^:private cli-options
  [["-v" "--version"]
   ["-h" "--help"]])

(def ^:private manifest-subaction-summary
  (->> (into []
             (for [[k v] (ns-publics 'cloture.manifest)]
               (format "  %1$-25s %2$s" k (:doc (meta v)))))
       (str/join \newline)))

(defn- usage
  [option-summary]
  (->> ["Clojure tool to look into jar files"
        ""
        "Usage: java -jar cloture.jar [OPTION] ACTION SUB-ACTION JAR [JAR...]"
        ""
        "Options:"
        option-summary
        ""
        "Actions:"
        "  manifest   Displays MANIFEST.MF attributes inside jar"
        ""
        "Sub-actions for manifest:"
        manifest-subaction-summary
        ""]
       (str/join \newline)))

(defn- error-msg
  [errors]
  (str "The following errors occurred while parsing your command:\n\n"
       (str/join \newline errors)))

(defn- exit
  [status msg]
  (println msg)
  (System/exit status))

(defn- version
  []
  (.get (doto (java.util.Properties.)
          (.load (io/reader (io/resource "META-INF/maven/cloture/cloture/pom.properties"))))
        "version"))

(defn- printfln
  {:static true}
  [fmt & args]
  (println (apply format fmt args)))

(defn -main
  ""
  [& args]
  (let [{:keys [options arguments errors summary]} (parse-opts args cli-options)]
    (cond
      (:help options) (exit 0 (usage summary))
      (:version options) (exit 0 (version))
      (< (count arguments) 3) (exit 1 (usage summary))
      errors (exit 1 (error-msg errors)))
    (let [[action sub & rest] args]
      (case action
        "manifest" (if-let [v (find-var (symbol "cloture.manifest" sub))]
                     (if (next rest)
                       (doseq [e rest] (printfln "%1$s: %2$s: %3$s" e sub (v e)))
                       (printfln "%1$s: %2$s"  sub (v (first rest))))
                     (exit 1 (usage summary)))
        (exit 1 (usage summary))))))
