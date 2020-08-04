(ns clojure-beginner._02_Loops)

;; loop like a for(int i = =0; i<10;i++)
(defn the-loop [x]
  (println "the-loop")
  (loop [i 0]                                               ;int i = 0
    (println "looping -> " i)
    (when (< i x)                                           ;i < x
      (recur (inc i)))))                                    ;i++

(the-loop 10)

;; loop do from 0 to x
(defn the-dotimes [x]
  (println "the-dotimes")
  (dotimes [i x]
    (println "looping with autoincrement i -> " i)))

(the-dotimes 10)

;; do while true loop
(defn the-while [x]
  (println "the-while")
  (def i (atom 0))
  (while (< @i x)
    (do
      (println "looping with while ->" @i)
      (swap! i inc))))

(the-while 10)

;; loop for each in a sequence
(defn the-foreach [xSeq]
  (println "the-foreach")
  (doseq [x xSeq]
    (println "traversing seq " x)))

(the-foreach [1 2 3 4])