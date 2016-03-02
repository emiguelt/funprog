; Hierarchical Structures

(define (count-leaves x)
  (cond ((null? x) 0)
        ((not (pair? x)) 1)
        (else (+ (count-leaves (car x))
                 (count-leaves (cdr x))))))

; test
(define x (list (list 1 2) (list 3 4)))
(count-leaves x)

; count-leaves iterative

(define (count-leaves x)
  (define (count-iter y a)
    (cond ((null? y) a)
          ((not (pair? y)) (+ a 1))
          (else (count-iter (car y) (count-iter (cdr y) a)))))
  (count-iter x 0))

; test
(define x (list (list 1 2) (list 3 4)))
(count-leaves x)

; Exercise 2.18 - Fringe method (return the leaves a a list)

(define (fringe list1)
  (cond ((null? list1) nil)
        ((not (pair? list1)) (cons list1 nil))
        (else (append (fringe (car list1)) (fringe (cdr list1))))))

(define x (list (list 1 2) (list 3 4)))
(fringe x)

