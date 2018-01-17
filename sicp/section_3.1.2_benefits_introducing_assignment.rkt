; 3.1.2 The benefits of introducing assignment

;; Approximation to pi using monte-carlo
(define rand
  (lambda ()
    (+ 1 (random 10))))

(define (estimate-pi trials)
  (sqrt (/ 6 (monte-carlo trials cesaro-test))))
(define (cesaro-test)
  (= (gcd (rand) (rand)) 1))
(define (monte-carlo trials experiment)
  (define (iter trials-remaining trials-passed)
    (cond ((= trials-remaining 0)
           (/ trials-passed trials))
          ((experiment)
           (iter (- trials-remaining 1) (+ trials-passed 1)))
          (else
            (iter (- trials-remaining 1) trials-passed))))
  (iter trials 0))


(estimate-pi 2)

;; Exercise 3.6 Random with reset
(define rand-init 1)
(define (rand-update x) (+ x 1));; <--- not as random as expected, but useful

(define rand-3.6
  (let ((x rand-init))
    (let ((rand (lambda ()
                (set! x (rand-update x) )
                x)))
      (define (dispatch param)
        (cond ((eq? param 'reset) (lambda (new-value) (set! x new-value)))
              ((eq? param 'generate) (rand))))
      dispatch)))

