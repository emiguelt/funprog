; Section 2.1.2 Abstraction barriers

; Exercise 2.2 Representing line segments in a plane

(define (point x y)(cons x y))
(define (x-point p)(car p))
(define (y-point p)(cdr p))

(define (make-segment p1 p2)(cons p1 p2))
(define (start-segment seg)(car seg))
(define (end-segment seg)(cdr seg))

(define (avg a b)(/ (+ a b) 2))

(define (midpoint-segment seg)
  (let ((p1 (start-segment seg))
        (p2 (end-segment seg)))
    (point (avg (x-point p1) (x-point p2))
           (avg (y-point p1) (y-point p2)))))

(define (print-point p)
  (newline)
  (display "(")
  (display (x-point p))
  (display ",")
  (display (y-point p))
  (display ")"))

;test

(define line1 (make-segment (point 0 0) (point 2 2)))
(print-point (midpoint-segment line1))

