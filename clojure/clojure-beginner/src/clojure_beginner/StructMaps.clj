(ns clojure-beginner.StructMaps)

;; StructMaps constructors

(defstruct animal :name :kind)

(def someAnimal (struct animal "Max" "cat"))
(println someAnimal)

(def otherAnimal (struct-map animal :name "Mickey" :kind "dog"))
(println otherAnimal)

;; Accessor
(println (:name someAnimal))
(println (:kind otherAnimal))

;; Creating from existing
(println (assoc someAnimal :name "Kitty"))                  ;;Changing existing property
(println (assoc someAnimal :age "5"))                       ;;Adding more properties
