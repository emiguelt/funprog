;; expt (basic)

(define (expt b n)
  (if (= n 0)
      1
      (* b (expt b (- n 1)))))

;; expt (iterative)
(define (exptit b n)
  (define (expt-iter counter product)
    (if (= counter 0)
        product
	(expt-iter (- counter 1) (* b product))))
  (expt-iter n 1))

;; expt (fast - recursive)
(define (even? n)
  (= (remainder n 2 ) 0))

(define (fast-expt b n)
  (cond ((= n 0) 1)
        ((even? n) (square (fast-expt b (/ n 2))))
	(else (*b (fast-expt b (- n 1))))))

;; Exercise 1.16 Define an iterative algorithm for fast-expt

;; Exercise 1.17 Use a similar algorithm of fast-expt for multiplication using repeated additions

;; Exercise 1.18 Use 1.16 and 1.17 to create an iterative algorithm for multiplication based on adding, doubling and halving
