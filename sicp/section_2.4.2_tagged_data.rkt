; Tagged data
;; Identify data with tags

;; Complex representation using tags

;;; tag constructors/selectors
(define (attach-tag type-tag contents)
  (cons type-tag contents))
(define (type-tag datum)
  (if (pair? datum)
    (car datum)
    (error "bad tagged datum -- TYPE-TAG" datum)))
(define (contents datum)
  (if (pair? datum)
    (cdr datum)
    (error "bad tagged datum -- CONTENTS" datum)))

;;; functions using tags
((define (rectangular? z)
  (eq? (type-tag z) 'rectangular))
define (polar? z)
  (eq? (type-tag z) 'polar))

;;; complex representation 1
(define (real-part-rectangular z) (car z))
(define (imag-part-rectangular z) (cdr z))
(define (magnitude-rectangular z)
  (sqrt (+ (square (real-part-rectangular z))
           (square (imag-part-rectangular z)))))
(define (angle-rectangular z)
  (atan (imag-part-rectangular z) 
        (real-part-rectangular z)))
(define (make-from-real-imag-rectangular x y)
  (attach-tag 'rectangular (cons x y)))
(define (make-from-mag-ang-rectangular r a)
  (attach-tag 'rectangular
              (cons (* r (cos a)) (* r (sin a)))))

;;; complex representation 2 (polar) (ommitted)...

;;; the selectors must check the tag to define the correct operation to be executed
(define (real-part z)
  (cond ((rectangular? z)
         (real-part-rectangular (contents z)))
        ((polar? z)
         (real-part-polar (contents z)))))
;;; other operations: magnitud, add, multiplication, etc.
