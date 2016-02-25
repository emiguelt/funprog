; 2.2.1 Representing lists
; a list can be represented as sequencial pairs (cons 1 (cons 2 (cons 3 nil)))

; List operations

; list-ref, return the nth number from a list
(define (list-ref items n)
  (if (= n 0)
    (car items)
    (list-ref (cdr items) (- n 1))))

;test
(define listA (list 2 4 6 8 10))

(list-ref listA 3)

; length (iterative)
(define (length items)
  (define (length-iter sublist count)
    (if (null? sublist)
      count
      (length-iter (cdr sublist) (+ count 1))))
  (length-iter items 0))

;test
(length listA)

; append l1 to l2
(define (append l1 l2)
  (if (null? l1)
    l2
    (cons (car l1) (append (cdr l1) l2))))

;test
(define listBl (list 1 2 3))
(define app1 (append listA listBl))
(display listA)
(display listBl)
(display app1)

; Exercise  2.17 Implement last-pair function
(define (last-pair items)
  (cond ((null? items) "Error, expected a non empty list")
        ((null? (cdr items)) (car items))
        (else (last-pair (cdr items)))))

(last-pair (list 1 2 3 4))
(last-pair nil)

; Exercise 2.18 Implement reverse function
(define (reverse items)
  (if (null? (cdr items))
    items
    (cons (reverse (cdr items)) (cons (car items) nil))))

(define listC (reverse (list 1 2 3 4)))
(display listC)
(last-pair listC)


