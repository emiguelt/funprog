; functions

(print "hello")

;Anonymous function
(fn [arg] (println arg))
((fn [arg] (println arg)) "hello world")

(#(println %1 %2) "hello" "miguel")

; Named functions
(def sayHello
  (fn [arg] (println "Hello "  arg)))

(sayHello "Miguel")

;; shorcut for def 
(defn sayHello [arg]
  (println "Hello " arg))
  ;;can add more expression here. Last one is returned

(sayHello "Miguel")

; Clojure use a singles pass compiler. So a function will be
; used, it must be defined or _declared_ before using the
; _declare_ macro

; Higher-Order functions
; Functions that take other functions as parameters

(map #(* % %) [1 2 3 4 5]) ;=> [1 4 9 16 25]

(defn concat-fields [& fields]
  (clojure.string/join ", " (remove empty? fields)))

(concat-fields "" "1 Main street" "Toronto" nil "Canada")

; Closures
;; Returning functions

(defn greeting [greeting-string]
  #(println greeting-string %))

(let [greet (greeting "Welcome to clojure")]
  (greet "Miguel")
  (greet "Edwin"))

; Threading expressions
;; Change the order of the functions calls to be more understable

(reduce + (interpose 5 (map inc (range 10))))

; is equivalent to

(->> (range 10) (map inc) (interpose 5) (reduce +))

; the result of a previous expression is passed as argument for the current expression.

; CODE STRUCTURE
; In clojure procedures are made by chaining functions together and piping input through them, or using the ->> macro to flat the procedure.

; Destructuring
; access values in data structures declaratively, also can be use in list, maps, etc.

(let [[smaller bigger] (split-with #(< % 5) (range 10))]
  (println smaller bigger))

; var args use [& args] notation
(defn print-args [& args]

; Namespaces
; used to create families of functions
 (ns colors)
 ..
 def functions..
 ..

;; Usually each file corresponds to one namespace identified by the file's path

;; functions in same namespace are accessible directly, externally must be used

;;; * _use_ keyword: to make all its Vars implicitly available
;;; * __require keyword: to access to Vars using the prefix. Can asign an alias with _as_.

;; WARN: If a namespace name has an hyphen, the corresponding file name should have and underscore
; POLYMORPHISM
;; Multimethods: Provides a dispatching mechanism suing a selector function associated with one or more methods

;;; example

(defmulti area :shape) ;<-- define multimethod

(defmethod area :circle [{:keys [r]}] ;<-- define implementation
  (* Math/PI r r))

(defmethod area :rectangle [{:keys [l w]}];-- define other implementation
  (* l w))

(defmethod area :default [shape]
  (throw (Exception. (str "unreconized shape: " shape))))

;;;; testing
(area {:shape :circle :r 10})
(area {:shape :rectangle :l 5 :w 10})

;; Protocols: Allow defining an abstract set of functions that can be implemented by a concrete type

;;; example
(defprotocol Foo  ;<-- define de interface
  "Foo doc string"
  (bar [this b] "bar doc string")
  (baz [this][this b] "baz doc string"))


(deftype Bar [data] Foo ;<-- implements Foo
  (bar [this param]
    (println data param))
  (baz [this]
    (println (class this)))
  (baz [this param]
    (println param)))

;;;; testing
(let [b (Bar. "some data")]
  (.bar b "param")
  (.baz b)
  (.baz b "baz with param"))

; Dealing with Global State
;; Atom: Used when uncoordinated updates are needed
(def global-val (atom nil)) ; <-- define an atom called global val.

(println (deref global-val)) ;<-- access the atom
(println @global-val) ;<-- shortcut accessor

(reset! global-val 10) ;<-- udpate value
(println @global-val)

(swap! global-val inc) ;<-- update value with function
(println @global-val)

;; Ref: Multiple updates as a transaction need to be done

(def names (ref []))

(dosync
  (ref-set names ["john"])
  (alter names #(if (not-empty %)
                  (conj % "jane") %)))


; Clojure macro system
;; For code templating
(defmacro defprivate [name args & body]
  `(defn ~(symbol name) ~args
     (if (:user @session)
       (do ~@body)
       "please log in")))

;;; ~ : used to evaluate the parameter (unquoting)
;;; ~@ : (unquote splicing) used when dealing with a sequence
;;; ` : treat the followind list as data instead of executing it (syntax quoting)

(macroexpand-1 '(defprivate foo [greeting] (println greeting)))

; Calling out to Java
; Importing classes
(:import java.io.File)
(:import java.io File FileInputStream FileOutputStream) ;<-- import many at same time

; instantiating classes (both equivalents):
(new File ".")
(File. ".") 

; calling methods
(let [f (File. ".")]
  (println (.getAsolutePath f))) ;<-- access non-static method

(str File/separator "foo" File/separator "bar") ;<-- access static method

(.getBytes (.getAbsolutePath (File. "."))) ;<-- access nested methods, same than:
(.. (File. ".") getAbsolutePath getBytes)


