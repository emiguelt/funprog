(ns elements_1_3)
(defn square [x] (* x x))
(defn square_sum [x y] (+ (square x) (square y)))
(defn square_sum_larger [a b c]
  (if (> a b)
    (square_sum a (if (> b c)
                    b
                    c))
    (square_sum b (if (> a c)
                    a
                    c))))
