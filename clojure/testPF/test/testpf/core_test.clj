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