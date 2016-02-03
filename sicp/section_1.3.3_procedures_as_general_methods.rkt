; Section  1.3.3 Procedures as general methods

(define (search f neg-point pos-point)
  (let ((midpoint (average neg-point pos-point)))
    (if (close-enough? neg-point pos-point)
      midpoint
      (let ((test-value (f midpoint)))
        (cond ((positive? test-value)
               (search f neg-point midpoint))
              ((negative? test-value)
               (search f midpoint pos-value))
              (else midpoint))))))

(define (average x y) (/ (+ x y) 2.0))

(define (close-enough? x y )
  (< (abs (- x y)) 0.001))

(search (lambda (x) x) -5 5)

