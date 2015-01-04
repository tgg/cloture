# cloture

Cloture is a simple tool to inspect jar files.

[![Clojars Project](http://clojars.org/cloture/latest-version.svg)](http://clojars.org/cloture)

## Installation

Download it from https://clojars.org/cloture.

## Usage

Cloture can be invoked with:

    $ java -jar cloture-0.1.0-standalone.jar [OPTION] ACTION SUB-ACTION JAR [JAR...]

## Options
* `-v`, `--version`           Displays Cloture version
* `-h`, `--help`              Displays Cloture help

## Actions

The only supported action as of now is:
* `manifest`                  Displays MANIFEST.MF attributes inside jar

## Sub-actions

`manifest` action supports the following sub-actions:
* `extension-installation`    Retrieves EXTENSION_INSTALLATION
* `implementation-url`        Retrieves IMPLEMENTATION_URL
* `specification-title`       Retrieves SPECIFICATION_TITLE
* `implementation-vendor-id`  Retrieves IMPLEMENTATION_VENDOR_ID
* `implementation-title`      Retrieves IMPLEMENTATION_TITLE
* `specification-vendor`      Retrieves SPECIFICATION_VENDOR
* `sealed`                    Retrieves SEALED
* `implementation-version`    Retrieves IMPLEMENTATION_VERSION
* `implementation-vendor`     Retrieves IMPLEMENTATION_VENDOR
* `specification-version`     Retrieves SPECIFICATION_VERSION
* `extension-name`            Retrieves EXTENSION_NAME
* `class-path`                Retrieves CLASS_PATH
* `signature-version`         Retrieves SIGNATURE_VERSION
* `manifest-version`          Retrieves MANIFEST_VERSION
* `content-type`              Retrieves CONTENT_TYPE
* `main-class`                Retrieves MAIN_CLASS
* `extension-list`            Retrieves EXTENSION_LIST

## Examples

```
    $ java -jar cloture-0.1.0-standalone.jar manifest main-class /usr/share/java/ant.jar /usr/share/java/bsh.jar
    /usr/share/java/ant.jar: main-class: org.apache.tools.ant.Main
    /usr/share/java/bsh.jar: main-class: bsh.Console
```

This shows the main class defined in the two jars specified.

### Bugs

This is a very early version that has not been tested extensively.

Please report issues to: https://github.com/tgg/cloture/issues.

## License

Copyright © 2014, 2015 Thomas Girard <thomas.g.girard@free.fr>

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
