Introduction
============
A library for bypassing private & protect fields & methods on java classes. Extracted from old contrib. Derived from work by hiredman.


Installation
============
```clojure
[clj-wallhack "1.0"]
```

Usage
=====
```clojure
(wall.hack/field foo.bar 'field obj)
```
This returns the private/protected field named "field" on obj. class is the class where field is defined, which might be an ancestor of (class obj)

```clojure
(wall.hack/method foo.bar "aMethodCall" [Integer Double] obj 3 5.0)
```
