; Section 1.3.2 Lambdas & Lets

; Variable's values are computed out of the "let"

((lambda (x)
   (let ((x 3)
         (y (+ x 2 )))
     (* x y ))) 2)

; >> 12

; 1.34  

(define ( f g )
  (g 2))

(f f)

; application: not a procedure;
;  expected a procedure that can be applied to arguments
;   given: 2
; [,bt for context]
; 
