; 2.3.1 Symbolic data - Quotation
;; Allows to work with symbols
(define a 1)
(define b 2)
(list a b)
(list 'a 'b)
(list 'a b)
(car '(a b c))
'()

; compare symbols
(eq? 'a 'b)
(eq? 'a 'a)

; look for a symbol in a tree, return the list where if found or false
(define (memq item x)
  (cond ((null? x) false)
        ((eq? item (car x)) x)
        (else (memq item (cdr x)))))

(memq 'apple '(pear banan prune))
(memq 'apple '(x (apple sauce) y apple pear))

; ex 2.53
(list 'a 'b 'c) ; (a b c)
(list (list 'george)) ; ((george)
(cdr '((x1 x2) (y1 y2))) ; ((y1 y2))
(car '((x1 x2) (y1 y2))) ; (x1 x2)

; ex 254 - equal list
(define (equal? l1 l2)
  (cond ((and (null? l1) (null? l2)) true)
        ((or (null? l1) (null? l2)) false)
        (else (and (eq? (car l1) (car l2))
                   (equal? (cdr l1) (cdr l2))))))

(equal? '(this is a list) '(this is a list))
(equal? '(this is a list) '(this (is a) list))

; 2.3.2 Symbolic Differentiation

;; Representing algebraic expressions
(define (variable? x) (symbol? x))
(define (same-variable? v1 v2)
  (and (variable? v1) (variable? v2) (eq? v1 v2)))
(define (make-sum a1 a2) (list '+ a1 a2))
(define (make-product m1 m2) (list '* m1 m2))
(define (sum? x) (and (pair? x) (eq? (car x) '+)))
(define (addend s) (cadr s))
(define (augend s) (caddr s))
(define (product? x) (and (pair? x) (eq? (car x) '*)))
(define (multiplier p) (cadr p))
(define (multiplicand p) (caddr p))

