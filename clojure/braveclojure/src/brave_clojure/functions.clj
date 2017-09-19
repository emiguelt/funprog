(ns brave-clojure.functions)

(def filename "resources/suspects.csv")

(def vamp-keys [:name :glitter-index])

(defn str->int [str] (Integer. str))

(def conversions {:name identity
                  :glitter-index str->int})

(defn convert [vamp-key value] ((get conversions vamp-key) value))

(defn parse "Conver a CSV file into rows of columns"
  [string]
  (map #(clojure.string/split % #",")
       (clojure.string/split string #"\n")))


(defn mapify "Return a seq of maps like {:name  \"Edward\" :glitter-index 10}"
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [vamp-key value]]
                   (assoc row-map vamp-key (convert vamp-key value)))
                 {}
                 (map vector vamp-keys unmapped-row)))
       rows))

(defn glitter-filter [minimum-glitter records]
  (filter #(>= (:glitter-index %) minimum-glitter) records))


(defn namelist "Ex 1 Turn the glitter result to a list of names"
  [fulllist]

  (map #(get % :name) fulllist)) 

(defn append "Ex 2 append a suspect to list"
  [the-list newone]
  (if (empty? the-list) 
    (cons newone nil)
    (cons 
      (first the-list) 
      (append (rest the-list) newone))))


(defn validate "Ex 3 add validation to append"
  [keys-to-validate record]
  (not (some (fn [the-key] (not (contains? record the-key)))
        keys-to-validate)))

(defn append-with-validate "ex 3"
  [the-list newone]
  (if (validate (keys conversions) newone)
    (append the-list newone)
    the-list))
