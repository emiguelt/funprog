(ns testpf.core-test
  (:require [clojure.test :refer :all]
            [testpf.core :refer :all]))

(deftest testEmptyString
  (testing "Test return true when empty string"
           (is (checkparenthesis nil))
           (is (checkparenthesis ""))
          ))

(deftest testFalseOneLeftParenthesis
  (testing "Is false when only one left parenthesis"
           (is (not (checkparenthesis "(")))))

(deftest testFalseOneRigthParenthesis
  (testing "Is false when only one rigth parenthesis"
           (is (not (checkparenthesis ")")))))

(deftest testTrueBalancedPairs
  (testing "Is true in balanced pairs"
           (is (checkparenthesis "()"))
           (is (checkparenthesis "(())"))
           (is (checkparenthesis "(a(b))"))
           ))

(deftest testReversePair
  (testing "Is false when first close then open"
           (is (not (checkparenthesis ")(")))))