(ns testpf.fold)

(defn fold-left [a listt f]
  (if (empty? listt)
    a
    (fold-left (f a (first listt)) (rest listt) f)))
