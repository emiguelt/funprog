# Elements
## Exercise 1.3. Define a procedure that takes three numbers as arguments and returns the sum of the squares of the two larger numbers.

### Solution 1
    (define (square x) (* x x))
    (define (square_sum a b ) (+ (square a ) (square b )))


    (define (square-sum-larger a b c)
      (if (> a b) 
          (if ( > b c) 
              (square_sum a b)
              (square_sum a c))
          (if (> a c) 
              (square_sum a b)
              (square_sum b c)
              )
          )
      )

### Solution 2 (Clojure)

    (ns elements)
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
