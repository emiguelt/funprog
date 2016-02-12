; Section 1.3.4 Returning functions

; fixed-point function from 1.3.3 section
(define tolerance 0.00001)
(define (fixed-point f first-guess)
  (define (close-enough? v1 v2)
    (< (abs (- v1 v2)) tolerance))
  (define (try guess)
    (let ((next (f guess)))
      (if (close-enough? guess next)
        next
        (try next))))
  (try first-guess))

;;;;;

(define (average x y) (/ (+ x y) 2.0))

(define (average-damp f)
  (lambda (x) (average x (f x))))

; reformulating the square root function
(define (sqrt x)
  (fixed-point (average-damp (lambda (y) (/ x y ))) 1.0))

(sqrt 10)
(sqrt 100)

