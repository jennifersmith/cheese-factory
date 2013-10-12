(ns cheese-factory.units)


;; continuing trend of making 1 x 10 ^n into 1 x n
;; unified atomic mass units
(defn grams [v]
  (int (* v 23)))

(defn kilos [v]
  (grams (* 1000 v) ))
;; of water...
(defn liters [v]
  (grams (* 1000 v)))

(defn cups [v]
  (liters (* v 0.2366)))
;; kelvin
(defn farenheit [v]
  (+ 273
     (* 5/9
        (- v 32))))
