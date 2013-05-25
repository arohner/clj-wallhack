Introduction
============
A library for bypassing private & protect fields & methods on java classes. Extracted from old contrib. Derived from work by hiredman.


Installation
============
```clojure
[clj-wallhack "1.0.1"]
```

Usage
=====

Getting a field
---------------
```clojure
(wall.hack/field foo.bar :field obj)
```
This returns the private/protected field named "field", declared in class "foo.bar" on instance obj. obj is an instanceof (or descendant of) class foo.bar. field can be anything named (a string, symbol or keyword).

Calling a method
----------------
```clojure
(wall.hack/method foo.bar :aMethodCall [Integer Double] obj 3 5.0)
```
This calls the private/protected method "aMethodCall" on obj. The method is declared in class foo.bar. obj is an instance of, or descendant of class foo.bar. the call takes a seq of classes that correspond to the method's signature, and an equal number of &rest arguments.

Pass nil instead of obj for static method calls.
