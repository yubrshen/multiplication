
(ns multiplication-table-of-prime.core-test
  (:require [midje.sweet :refer :all]
            [multiplication-table-of-prime.core :refer :all]
            ))

(facts "about `primes'"
       (fact "First prime number"
             (primes 1) => [2])
       (fact "More prime numbers"
             (primes 3) => [2 3 5])
       (fact "First 10 prime numbers"
             (primes 10) => [2 3 5 7 11 13 17 19 23 29]))

(facts "about `multi'"
       (fact "Some multiplications"
             (multi 3 (range)) => {:matrix [[0 0 0]
                                    [0 1 2]
                                    [0 2 4]]
                                   :max 4
                                   :row-header [0 1 2]
                                   :column-header [0 1 2]}))

(facts "about `table-str'"
       (fact "matrix as a table as a string"
             (table-str {:matrix [[0 0 0]
                                      [0 1 2]
                                      [0 200 4]]
                         :max 200
                         :row-header [0 1 2]
                         :column-header [0 1 2]})
             => "       0   1   2\n   0   0   0   0\n   1   0   1   2\n   2   0 200   4")
 )

(facts "about `multi-tab-primes'"
       (fact "multiplication table for the first 3 prime numbers"
             (multi-tab-primes 3) =>
             "     2  3  5\n  2  4  6 10\n  3  6  9 15\n  5 10 15 25")
 )
