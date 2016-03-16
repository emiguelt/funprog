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
(define (=number? exp num)
  (and (number? exp) (= exp num)))
(define (variable? x) (symbol? x))
(define (same-variable? v1 v2)
  (and (variable? v1) (variable? v2) (eq? v1 v2)))
(define (make-sum a1 a2)
  (cond ((=number? a1 0) a2)
        ((=number? a2 0) a1)
        ((and (number? a1) (number? a2)) (+ a1 a2))
        (else (list '+ a1 a2))))
(define (make-product m1 m2) 
  (cond ((or (=number? m1 0) (=number? m2 0)) 0)
        ((=number? m1 1) m2)
        ((=number? m2 1) m1)
        ((and (number? m1) (number? m2)) (* m1 m2))
        (else (list '* m1 m2))))
(define (sum? x) (and (pair? x) (eq? (car x) '+)))
(define (addend s) (cadr s))
(define (augend s) (caddr s))
(define (product? x) (and (pair? x) (eq? (car x) '*)))
(define (multiplier p) (cadr p))
(define (multiplicand p) (caddr p))

;; Differentiation
(define (deriv exp var)
  (cond ((number? exp) 0)
        ((variable? exp)
         (if (same-variable? exp var) 1 0))
        ((sum? exp)
         (make-sum (deriv (addend exp) var)
                   (deriv (augend exp) var)))
        ((product? exp)
         (make-sum
           (make-product (multiplier exp)
                         (deriv (multiplicand exp) var))
           (make-product (deriv (multiplier exp) var)
                         (multiplicand exp))))
        (else
          (error "unknown expression type -- DERIV" exp))))

;;; test
(display (deriv '(+ x 3) 'x))
(display (deriv '(* x y) 'y))
(display (deriv '(* (* x y) (+ x 3)) 'x))

