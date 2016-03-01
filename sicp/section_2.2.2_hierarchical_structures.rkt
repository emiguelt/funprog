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


