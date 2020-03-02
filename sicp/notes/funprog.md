# Functional programming

# Building abstractions with procedures
## Elements of programming
### primitive expressions

We start representing a simple element, for example a number. A number is a expression that denotes a numeric value:

```java
//in Java
int x = 1;
System.out.println(x); // it will print 1 in the console
```
```lisp
# In Lisp the native number is represented this way and is printed in the same way
1
```

Other expressions can be _primitive procedures_ such as _+_ _-_ and can be combined with numerals:

```java
//In Java
int r = 1 + 2;
```
```lisp
# In Lisp
(+ 1 2)
```

These combinations generates a result, in this case another number.

### Means of combination

* **Naming and Environment**: A programming language must give the means for naming the objects we want to use (variables). These names are set in a space of memory and are available to be used in our programs with a defined scope. These set of variables are the _Environment_

```java
//Java
SomeType x = new SomeType();  //Assigning getType() value to the x variable
int y = 1;
```

```lisp
; Lisp
(define x 2)  # Assigning 2 to x variable
(define x-plus2 (+ x 2))
```

### Means of abstraction

* **Procedure definition**: Define how to compound operations to be referred as a unit. The _compound procedures_ are defined by using a language keyword, a name, parameters and a body (that has the logic of the combination)

```java
// Java
class A{
  //<access modifier> <return type> <name>(<formal params>){ <body>}
  public int sum(int a, int b){return a + b;}
}
```
```lisp
; Lisp
(define (<name> <formal parameters>) <body>)
(define (square x) (* x x))
```


#### Substitution models

* **Applicative order**

```lisp
(sum-of-squares (+ a 1) (* a 2))
(sum-of-squares (+ 5 1) (* 5 2))
(+ (square 6) (square 10))
(+ (* 6 6) (* 10 10))
(+ 36 100)
136
```

* **Normal order**

```lisp
(sum-of-squares (+ 5 1) (* 5 2))
(+ (square (+ 5 1)) (square (* 5 2))  (+ (*(+ 5 1)(+ 5 1)) (* (* 5 2) (* 5 2)))
(+ (* 6 6) (* 10 10))
(+ 36 100)
136
```

#### Conditionals

* **cond**

```lisp
; Lisp
(cond (<p1> <e1>)
      (<p2> <e2>)
      ...
      (<pn> <en>)
      (else <em>)
)
```

* **if**

```lisp
; Lisp
(if <p1> <doiftrue> <doiffalse>)
```

* **Predicates**: Expression that returns boolean values.

```lisp
; Lisp
(> 1 2)
(< 1 2)
(and <e1>...<en>)
(or <e1>...<en>)
(not <e1>)
```


### Exercises

* Given a number, calculate the square if even or the cube if odd
* Given a number, return the same number if lower than 20, return square if lower than 50 or return 2*square if bigger or equal to 50

## Linear recursion & Iteration

### Linear recursion

```
   n! = n (n-1)!   <-- This is equivalent to a recursion in programming
```

Basic implementation: 

```lisp
; Lisp
(define (sum-integers a b)
  (if (> a b)
      0
          (+ a (sum-integers (+ a 1) b))))

(sum-integers 1 5)

; sum of cubes
(define (cube x) (* x x x))
(define (sum-cubes a b)
  (if (> a b)
      0
          (+ (cube a) (sum-cubes (+ a 1) b))))

(sum-cubes 1 5)

```
### Iteration

```lisp
; Lisp

(define (sum term a next b)
  (define (sum-iter a result)
      (if (> a b)
            result
                  (sum-iter (inc a) (+ result (term a)))))
    (sum-iter a 0))
```



## Three recursion

```lisp
; Lisp
; Fibonacci tree recursive

(define (fibtr n)
  (cond ((= n 0) 0)
          ((= n 1) 1)
                  (else (+ (fibtr (- n 1))
                                   (fibtr (- n 2))))))

(fibtr 5)
; 5
(fibtr 6)
;6

; Fibonacci iterative

(define (fibit n)
  (define (fib-iter a b count)
      (if (= count 0)
            b
                  (fib-iter (+ a b) a (- count 1))))

    (fib-iter 1 0 n)
      )

(fibit 5)
;5
(fibit 6)
;6
```


## Growth orders

**PENDING**

# Formulating abstractions with high order procedures

## Procedures as arguments

We create a template for common procedure to execute iterative operations, and rewrote the sum of integers and sum of cubes using this template

```lisp
; sum template
(define (sum term a next b)
  (if (> a b)
      0
          (+ (term a) (sum term (next a) next b))))

; sum-integer 2 (using template)
(define (identity x) x)
(define (inc x) (+ x 1))
(define (sum-integers2 a b)
  (sum identity a inc b))

(sum-integers2 1 5)

; sub-cubes 2 (using template)
; sub-cubes 2 (using template)

(define (sum-cubes2 a b)
  (sum cube a inc b))

(sum-cubes2 1 5)
```

## Lambdas

## Procedures as returned values

## Pairs, Lists

# Building abstractions with data

iData abstraction separates procedures from data (make procedure independent)

## Pairs

# Immutablity
# Functors
 * https://dzone.com/articles/functor-and-monad-examples-in-plain-java 
# Monads
## What is?
## Operations
## Optional monad 
    * https://nazarii.bardiuk.com/posts/java-monad.html
    * https://medium.com/@afcastano/monads-for-java-developers-part-1-the-optional-monad-aa6e797b8a6e)
    * https://github.com/jasongoodwin/better-java-monads
## The monad pattern
   * https://medium.com/@afcastano/monads-for-java-developers-part-2-the-result-and-log-monads-a9ecc0f231bb
## Either
## Try monad
    * https://medium.com/swlh/write-a-monad-in-java-seriously-50a9047c9839
# List in functional programming 
## Map
## Filter
## Foreach
## Fold
## Reduce
## Collect
# Stream
 * https://stackify.com/streams-guide-java-8/
