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

;; independent withdraws
(define (make-withdraw balance)
  (lambda (amount)
    (if (>= balance amount)
      (begin (set! balance (- balance amount))
             balance)
      "Insufficient funds")))

(define w1 (make-withdraw 100))
(w1 50)
(w1 60)

(define w2 (make-withdraw 50))
(w2 30)
(w2 100)

;; define account (withdraw and deposit)
(define (make-account balance)
  (define (withdraw amount)
    (if (>= balance amount)
      (begin (set! balance (- balance amount))
             balance)
      "Insufficient funds"))
  (define (deposit amount)
    (set! balance (+ balance amount))
    balance)
  (define (dispatch m)
    (cond ((eq? m 'withdraw) withdraw)
          ((eq? m 'deposit) deposit)
          (else (error "Unknown request -- MAKE ACCOUNT"
                       m))))
  dispatch)

(define ac1 (make-account 100))
((ac1 'withdraw) 10)
((ac1 'deposit) 20)


;; Exercise 3.1  Accumulator
(define (make-accumulator amount)
  (lambda (addval)
    (set! amount (+ amount addval))
    amount))

(define acc1 (make-accumulator 0))
(acc1 10)
(acc1 20)


;; Exercise 3.2 Method monitor
(define (make-monitored f)
  (define calls 0)
  (define (how-many-calls?) calls)
  (define (reset-count)
    (set! calls 0)
    "Counter reset to 0")
  (define (callmethod param)
    (set! calls (+ calls 1))
    (f param))
  (define (dispatch param)
    (cond ((eq? param 'how-many-calls?) (how-many-calls?))
          ((eq? param 'reset-count) (reset-count))
          (else (callmethod param))))
  dispatch)

(define s (make-monitored sqrt))
(s 'how-many-calls?)
(s 100)
(s 'how-many-calls?)
(s 'reset-count)


;; Exercise 3.3 Bank account with password
(define (make-account-pwd amount pass-account)
  (define account (make-account amount))
  (lambda (pass operation)
    (cond ((eq? pass-account pass) (account operation))
          (else (lambda (x) "Incorrect password")))))

(define acc (make-account-pwd 100 'my-pass))

((acc 'my-pass 'withdraw) 10)

((acc 'my-pass 'withdraw) 1000)

((acc 'other-passs 'withdraw) 10)

;; Exercise 3.7 Joint account with two passwords
(define (make-joint account pass-account pass-joint)
  (lambda (pass operation)
    (cond ((eq? pass pass-joint) (account pass-account operation))
          (else (account pass operation)))))

(define acc (make-account-pwd 100 'my-pass))

(define joint-acc (make-joint acc 'my-pass 'pass2))

((joint-acc 'my-pass 'withdraw) 10)

((joint-acc 'pass2 'withdraw) 10)

((joint-acc 'other-passs 'withdraw) 10)


