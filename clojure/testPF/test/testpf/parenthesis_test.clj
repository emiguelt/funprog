(ns testpf.parenthesis-test
  (:require [clojure.test :refer :all]
            [testpf.parenthesis :refer :all]))

(deftest testEmptyString
  (testing "Test return true when empty string"
           (is (parenthesisBalanced nil))
           (is (parenthesisBalanced ""))
          ))

(deftest testFalseOneLeftParenthesis
  (testing "Is false when only one left parenthesis"
           (is (not (parenthesisBalanced "(")))))

(deftest testFalseOneRigthParenthesis
  (testing "Is false when only one rigth parenthesis"
           (is (not (parenthesisBalanced ")")))))

(deftest testTrueBalancedPairs
  (testing "Is true in balanced pairs"
           (is (parenthesisBalanced "()"))
           (is (parenthesisBalanced "(())"))
           (is (parenthesisBalanced "(a(b))"))
           ))

(deftest testReversePair
  (testing "Is false when first close then open"
           (is (not (parenthesisBalanced ")(")))))

(deftest tests
  (testing "teacher tests"
    (is (parenthesisBalanced "((1+2) + 4)"))
    (is (not (parenthesisBalanced "(1+2")))))
