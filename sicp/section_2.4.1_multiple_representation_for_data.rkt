; 2.4 Multiple representations for data

;; 2.4.1 Representing complex numbers

(define (square a) (* a a))

;;; Complex number representation 1
(define (real-part z) (car z))
(define (imag-part z) (cdr z))
(define (magnitude z)
  (sqrt (+ (square (real-part z)) (square (imag-part z)))))
(define (angle z)
  (atan (imag-part z) (real-part z)))
(define (make-from-real-imag x y) (cons x y))
(define (make-from-mag-ang r a)
  (cons (* r (cos a)) (* r (sin a))))

;;; Complex number representation 2
(define (magnitude z) (car z))
(define (angle z) (cdr z))
(define (real-part z)
  (* (magnitude z) (cos (angle z))))
(define (imag-part z)
  (* (magnitude z) (sin (angle z))))
(define (make-from-real-imag x y)
  (cons (sqrt (+ (square x) (square y)))
        (atan y x)))
(define (make-from-mag-ang r a) (cons r a))

;;; Operations
(define (gen-add op z1 z2)
  (make-from-real-imag (op (real-part z1) (real-part z2))
                       (op (imag-part z1) (imag-part z2))))
(define (gen-prod op1 op2 z1 z2)
  (make-from-mag-ang (op1 (magnitude z1) (magnitude z2))
                     (op2 (angle z1) (angle z2))))

(define (add-complex z1 z2)
  (gen-add (lambda (a b) (+ a b)) z1 z2))
(define (sub-complex z1 z2)
  (gen-add (lambda (a b) (- a b)) z1 z2))
(define (mul-complex z1 z2)
  (gen-prod (lambda (a b) (* a b))
            (lambda (a b) (+ a b))
            z1 z2))
(define (div-complex z1 z2)
  (gen-prod (lambda (a b) (/ a b))
            (lambda (a b) (- a b))
            z1 z2))

;;; Tests
(define z1 (make-from-real-imag 1 2))
(define z2 (make-from-real-imag 4 3))

(display (add-complex z1 z2))
(display (sub-complex z1 z2))
(display (mul-complex z1 z2))
(display (div-complex z1 z2))

