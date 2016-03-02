; 2.2.3 Sequences as conventional interfaces
;; initial case
(define (square x) (* x x))
(define (sum-odd-squares tree)
  (cond ((null? tree) 0)
        ((not (pair? tree))
         (if (odd? tree) (square tree) 0))
        (else (+ (sum-odd-squares (car tree)) (sum-odd-squares (cdr tree))))))

;;; test
(sum-odd-squares (list (list 1 2) (list 3 4)))

;; it could be rewritten as a sequence of operations ->> enumerate->filter-odd,map-square,accumulate

(define (filter predicate seq)
  (cond ((null? seq) nil)
        ((predicate (car seq))
         (cons (car seq)
               (filter predicate (cdr seq))))
        (else (filter predicate (cdr seq)))))

;;; test
(filter odd? (list 1 2 3 4 5))

(define (accumulate op initial seq)
  (if (null? seq)
    initial
    (op (car seq)
        (accumulate op initial (cdr seq)))))

;;; test
(accumulate + 0 (list 1 2 3 4))

(define (range low high)
  (if (> low high)
    nil
    (cons low (range (+ low 1) high))))

;;; test
(range 1 5)

(define (enumerate-tree tree)
  (cond ((null? tree) nil)
        ((not (pair? tree)) (list tree))
        (else (append (enumerate-tree (car tree))
                      (enumerate-tree (cdr tree))))))

;;; test
(enumerate-tree (list (list 1 2)(list 3 4)))

; reformulating sum-odd-squares
(define (sum-odd-squares tree)
  (accumulate +
              0
              (map square
                   (filter odd?
                           (enumerate-tree tree)))))

;;; test
(sum-odd-squares (list (list 1 2) (list 3 4)))


