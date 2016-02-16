; Section 2.1 Introduction to data abstraction

;example representing rational numbers (using _cons_ procedure to get a _pair_)

(define (make-rat n d) (cons n d))
(define (numer x) (car x))
(define (denom x) (cdr x))

(define (print-rat x)
  (newline)
  (display (numer x))
  (display "/")
  (display (denom x)))

; defining operations
(define (sum-or-sub f x y)
  (make-rat (f (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))

(define (add-rat x y)
  (sum-or-sub (lambda (a b)(+ a b)) x y))

(define (sub-rat x y)
  (sum-or-sub (lambda (a b)(- a b)) x y))

(define (mul-rat x y)
  (make-rat (* (numer x) (numer y))
            (* (denom x) (denom y))))

(define (div-rat x y)
  (mul-rat x (make-rat (denom y) (numer y))))

; test
(define one-half (make-rat 1 2))
(print-rat one-half)

(define one-third (make-rat 1 3))
(print-rat one-third)

(print-rat (add-rat one-half one-third))
(print-rat (sub-rat one-half one-third))
(print-rat (mul-rat one-half one-third))
(print-rat (div-rat one-half one-third))

; Excercise 2.1 Support negative rationals in representation

(define (make-rat n d)
  (if (or (< n 0) (< d 0))
    (cons (- (abs n)) (abs d))
    (cons n d)))


