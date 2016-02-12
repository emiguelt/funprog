; Section  1.3.3 Procedures as general methods

; search the root of a funtion -> f(x)= 0

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


(define (half-interval-method f a b )
  (let ((a-value (f a))
        (b-value (f b)))
    (cond ((and (negative? a-value) (positive? b-value))
           (search f a b))
          ((and (positive? a-value) (negative? b-value))
           (search f b a))
          (else
            (error "Values are not of oposite sign" a b)))))

(half-interval-method (lambda (x) x) -5 5)
(half-interval-method (lambda (x) x) 5 5)
(half-interval-method (lambda (x) x) -5 -5)

; Finding fixed point of functions  -> f(x)=x

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

(fixed-point cos 1.0)
(fixed-point (lambda (y) (+ (sin y) (cos y))) 1.0)

; Finding the square root of a number
(define (sqrt x)
  (fixed-point (lambda (y) (average y (/ x y))) 1.0))


(sqrt 25)
(sqrt 9)
(sqrt 36)
(sqrt 25)

; Exercise 1.36 - Modify fixed-point to print the sequence of approximations

(define tolerance 0.00001)
(define (fixed-point f first-guess)
  (define (close-enough? v1 v2)
    (< (abs (- v1 v2)) tolerance))
  (define (try guess)
    (let ((next (f guess)))
      (newline)
      (display guess)
      (if (close-enough? guess next)
        next
        (try next))))
  (try first-guess))

(fixed-point (lambda (x) (/ (log 1000) (log x))) 100.0)
