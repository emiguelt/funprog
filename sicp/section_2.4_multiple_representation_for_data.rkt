; 2.4 Multiple representations for data

;; 2.4.1 Representing complex numbers


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

