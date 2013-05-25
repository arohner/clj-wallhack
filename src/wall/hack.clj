;   Copyright (c) Stuart Halloway & Contributors, April 2009. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file epl-v10.html at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.

;;
;; This library is extracted from 'old' clojure contrib. These fns appear to have been written by hiredman.
;; https://github.com/richhickey/clojure-contrib/commit/cc4e2ec2bf558f059330ebc97a031d7806a1e364

(ns wall.hack)

(defn method
  "Calls a private or protected method.

   class - the class where the method is declared
   params - a vector of Class which correspond to the arguments to the method
   obj - nil for static methods, the instance object otherwise
   method-name - something Named"
  [class method-name params obj & args]
  (-> class (.getDeclaredMethod (name method-name) (into-array Class params))
    (doto (.setAccessible true))
    (.invoke obj (into-array Object args))))

(defn field
  "Access to private or protected field. field-name must be something Named

   class - the class where the field is declared
   field-name - Named
   obj - the instance object, or a Class for static fields"
  [class field-name obj]
  (-> class (.getDeclaredField (name field-name))
    (doto (.setAccessible true))
    (.get obj)))

(defn mirror
  "returns an object that if used like (:foo returned-obj) the value
  of the foo field on the original object will be returned, even if
  the field is private.

  (:original/object returned-obj) will return the obj that was passed in"
  [obj]
  (reify
    clojure.lang.ILookup
    (valAt [this k]
      (.valAt this k nil))
    (valAt [this k not-found]
      (if (= k :original/object)
        obj
        (try
          (field (class obj) k obj)
          (catch Exception _
            not-found))))))
