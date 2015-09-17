(ns elements_1_6)
(defn abs[x]
  (cond (< x 0) (- x)
        :else x)
  )

(defn square[x] (* x x))

(defn good-enough? [guess x]
      (< (abs (- (square guess) x)) 0.0001))


(defn average [x y]
      (/ (* x y) 2)
      )
(defn improve [guess x]
      (average guess (/ x guess)))

(defn sqrt-iter [guess x]
      (if (good-enough? guess x)
        guess
        (sqrt-iter (improve guess x)
                   x)))

(defn sqrt [x]
      (sqrt-iter 1.0 x)
  )

"new if evaluates the predicate before"
(defn new-if[p t e]
  (cond (= true p) t
        :else e)
  )
