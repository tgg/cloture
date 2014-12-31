(ns ^{:doc "Displays MANIFEST.MF main attributes inside jar file

Main attributes names, in capital letters, are mapped to lowercase functions.

Examples of useful attributes:

1. MAIN_CLASS, available as main-class function.

2. CLASS_PATH, available as class-path function.

The full list of main attributes can be retrieved from:
  http://docs.oracle.com/javase/8/docs/api/java/util/jar/Attributes.Name.html"
      :author "Thomas Girard"}
    cloture.manifest
  (:import [java.net URL])
  (:require [clojure.string :as str]))

(defn- prepend-protocol
  [^String jar]
  (if (.contains jar ":")
    jar
    (str "file:" jar)))

(defn- jar-url
  [^String jar]
  (URL. (str "jar:" (prepend-protocol jar) "!/")))

(defn- manifest
  [^String jar]
  (let [url (jar-url jar)
        con (.openConnection url)]
    (.getManifest con)))

(defn- manifest-value
  [^String jar ^java.util.jar.Attributes$Name name]
   (->
    (.getMainAttributes (manifest jar))
    (.getValue name)))

(defmacro ^:private gen-attr-fn
  [name]
  `(defn ~(symbol (str/replace (.toLowerCase (str name)) \_ \-))
     {:doc (str "Retrieves " ~name) }
     [^String jar#]
     (manifest-value jar# ~(symbol (str "java.util.jar.Attributes$Name/" name)))))

(gen-attr-fn "MANIFEST_VERSION")
(gen-attr-fn "SIGNATURE_VERSION")
(gen-attr-fn "CONTENT_TYPE")
(gen-attr-fn "CLASS_PATH")
(gen-attr-fn "MAIN_CLASS")
(gen-attr-fn "SEALED")
(gen-attr-fn "EXTENSION_LIST")
(gen-attr-fn "EXTENSION_NAME")
(gen-attr-fn "EXTENSION_INSTALLATION")
(gen-attr-fn "IMPLEMENTATION_TITLE")
(gen-attr-fn "IMPLEMENTATION_VERSION")
(gen-attr-fn "IMPLEMENTATION_VENDOR")
(gen-attr-fn "IMPLEMENTATION_VENDOR_ID")
(gen-attr-fn "IMPLEMENTATION_URL")
(gen-attr-fn "SPECIFICATION_TITLE")
(gen-attr-fn "SPECIFICATION_VERSION")
(gen-attr-fn "SPECIFICATION_VENDOR")
