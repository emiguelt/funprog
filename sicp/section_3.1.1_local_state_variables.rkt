; 3.1.1 Local state variables

;; Using global state variable
(define balance 100)
(define (withdraw amount)
  (if (>= balance amount)
    (begin (set! balance (- balance amount))
           balance)
    "Insufficient funds"))

(withdraw 50)
(withdraw 60)

;; Using inner state
(define new-withdraw
  (let ((balance 100))
    (lambda (amount)
      (if (>= balance amount)
        (begin (set! balance (- balance amount))
               balance)
        "Insufficient funds"))))

(new-withdraw 50)
(new-withdraw 60)

(define (make-withdraw balance)
  (lambda (amount)
    (if (>= balance amount)
      (begin (set! balance (- balance amount))
             balance)
      "Insufficient funds")))

(define w1 (make-withdraw 100))
(w1 50)
(w1 60)



