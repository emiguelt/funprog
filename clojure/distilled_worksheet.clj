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

 ;; functions in same namespace are accessible directly, externally must be used
 ; use keyword
