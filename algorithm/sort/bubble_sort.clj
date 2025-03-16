(ns bubble-sort)

(defn- bubble [ys x]
  (println ys x)
  (if-let [y (peek ys)]
    (if (> y x)
      (conj (pop ys) x y)
      (conj ys x))
    [x]))

(defn bubble-sort [xs]
  (let [ys (reduce bubble [] xs)]
    (if (= xs ys)
      xs
      (recur ys))))

(println (bubble-sort [5 3 8 4 2 7]))
