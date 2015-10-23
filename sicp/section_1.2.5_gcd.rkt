# Section 1.2.5 Greatest common divisor

; gc
((define (gcd a b)
  (if (= b 0)
    a
    (gcd b (remainder a b))))

(define (rem a b)
  (printf "rem ~a ~a\n" a b)
  (remainder a b))

(define (gcd a b)
  (printf "gcd ~a ~a\n" a b)
  (if (= b 0)
    a
    (gcd b (rem a b))))

(gcd 10 4)
(gcd 206 40)


