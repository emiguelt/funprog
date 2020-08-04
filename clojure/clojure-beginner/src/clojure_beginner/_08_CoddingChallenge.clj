(ns clojure-beginner.-08-CoddingChallenge)

;; Car dealership with discount coupon

(defn dealer []
  (def car-list {'BMW     60000
                 'FERRARI 100000
                 'FIAT    20000})

  (def coupon-code "ABC")

  (fn [budged code]
    (println "Received budged: " budged "Code: " code)
    (def discount (if (= coupon-code code) 0.8 1.0))
    (println "Discount: " (- 1.0 discount))
    (filter
      (fn [[k v]] (<= v budged))
      (map (fn [[k v]] [k (* v discount)]) car-list)
      )
    )
  )

(println ((dealer) 50000 "ABC"))
(println ((dealer) 50000 "ABCD"))
