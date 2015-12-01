; Section 1.3.1 Procedures as arguments

; sum of integers
(define (sum-integers a b)
  (if (> a b)
    0
    (+ a (sum-integers (+ a 1) b))))

(sum-integers 1 5)

; sum of cubes
(define (cube x) (* x x x))
(define (sum-cubes a b)
  (if (> a b)
    0
    (+ (cube a) (sum-cubes (+ a 1) b))))

(sum-cubes 1 5)

; sum template
(define (sum term a next b)
  (if (> a b)
    0
    (+ (term a) (sum term (next a) next b))))

; sum-integer 2 (using template)
(define (identity x) x)
(define (inc x) (+ x 1))
(define (sum-integers2 a b)
  (sum identity a inc b))

(sum-integers2 1 5)

; sub-cubes 2 (using template)

(define (sum-cubes2 a b)
  (sum cube a inc b))

(sum-cubes2 1 5)

; integral
(define (integral f a b dx)
  (define (add-dx x) (+ x dx))
  (* (sum f (+ a (/ dx 2.0)) add-dx b) dx))

(integral cube 0 1 0.01)

; Exercise 1.29 - integral Simpson's rule

(define (integral-simp f a b n)
  (define (h) (/ (- b a) n))
  (define (y k) (* (factor k) (f (+ a (* k (h))))))
  (define (factor k) 
    (cond ((or (= k 0) (= k n)) 1)
          ((= (remainder k 2) 0) 2)
          (else 4))
    )
  (* (/ (h) 3.0) (sum y 0 inc n)))

(integral-simp cube 0 1 100)
(integral-simp cube 0 1 1000)
(integral-simp cube 0 1 10000)
(integral-simp cube 0 1 1000000)


; Exercise 1.30

(define (sum term a next b)
  (define (sum-iter a result)
    (if (> a b)
      result
      (sum-iter (inc a) (+ result (term a)))))
  (sum-iter a 0))


