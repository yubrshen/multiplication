
(ns multiplication-table-of-prime.core
(:gen-class))

(defn primes
  "Produces the first n primes starting from the smallest, 2. It returns a lazy sequence."

  [n]
  (if (= n 1)
    [2]
    (nth 
     (iterate
      (fn [seeds]
        (conj seeds (first
                     (filter (fn [c] (every? (fn [p] (< 0 (mod c p))) (filter (fn [p] (<= (* p p) c)) seeds)))
                             (map (partial + (last seeds))
                                  (filter even? (drop 1 (range)))))))) [2 3])
     (- n 2))))

(defn multi
  "Produces the multiplications of the first n numbers in numbers which may be a lazy-sequence, 
  returns a map with key matrix and value of the computed multiplication table, in terms of sequence of sequences, as sequence of rows
  and a key of max, with the maximum value in the matrix. 
  The maximum value may be used to for the width of numbers for properly formatting the multiplication table. 
  It also returns key of column-row, with value for the header for the columns, 
  and row-header, with value for each row in the table."

  [n numbers]
  (let [numbers-realized (take n numbers)
        *_remembered (memoize (fn [x y] (* x y)))
        matrix (for [x numbers-realized]
                 (for [y numbers-realized] (*_remembered x y)))
        max-value (#(*_remembered % %) (last numbers-realized))]
    {:matrix matrix :max max-value
     :row-header numbers-realized :column-header numbers-realized}))

(defn table-str 
  "generates the formated string to be displayed for the matrix, based on the output of multi. 
  It adds the column header, and row header, makes sure the columns are right-justified. "

  [{:keys [matrix max row-header column-header]}]
  (let [width (count (str max))]
    (let [number-str #(format (str " %" width "d") %)
          row-header-width (count (number-str (first row-header)))]
      (str
       (apply str (concat (repeat row-header-width " ") (map number-str column-header))) "\n"
       (clojure.string/join "\n"
                            (for [i (range (count matrix))]
                              (str (number-str (nth row-header i))
                                   (apply str (for [x (nth matrix i)] (number-str x))))))))))

(defn multi-tab-primes
  "Returns the string suitable to be displayed 
  for the multiplication table of the first n primes."

  [n]
  (let [prime-nbrs (time (primes n))
        mat (time (multi n prime-nbrs))
        s (time (table-str mat))]
    s
    ;; (->> prime-nbrs
    ;;      (multi n,)
    ;;      table-str)
    )
  )

(defn -main
  "Calculate and display multiplication table of the first 10 prime numbers."

  [& args]
  ;; Check the response time of generating the multiplication table.
  ;; (println (time (multi-tab-primes 10)))
  (println (multi-tab-primes 10))
  )
