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
(define (fast-exptit b n)
  (define (fast-expt-iter b n p)
    (cond ((= n 0) p)
          ((even? n) (fast-expt-iter (* b b) (/ n 2 ) p))
          (else (fast-expt-iter b (- n 1) (* b p)))))
  (fast-expt-iter b n 1))

(fast-exptit 2 0)
(fast-exptit 2 1)
(fast-exptit 2 2)
(fast-exptit 2 3)

(fast-exptit 5 0)
(fast-exptit 5 1)
(fast-exptit 5 2)
(fast-exptit 5 3)

;; Exercise 1.17 Use a similar algorithm of fast-expt for multiplication using repeated additions
(define (* a b)
  (if (= b 0 )
    0
    (+ a (* a (- b 1)))))

(* 2 3)
(* 7 8)
;;;; Multiplication iterative
(define (* a b)
  (define (*-iter a b s)
    (if (= b 0 )
      s
      (*-iter a (- b 1) (+ a s))))

  (*-iter a b 0))

(* 2 3)
(* 7 8)

;;;; Multiplication using double and halve
(define (doubles a) (+ a a))
(define (halves a) (/ a 2))

;;;;;; recursive
(define (* a b)
  (cond ((= b 0 ) 0)
        ((even? b) (* (doubles a) (halves b)))
        (else (+ a (- b 1)))))

(* 2 3)
(* 7 8)

;; Exercise 1.18 Use 1.16 and 1.17 to create an iterative algorithm for multiplication based on adding, doubling and halving
