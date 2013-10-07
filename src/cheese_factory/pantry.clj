(ns cheese-factory.pantry
  (:require [cheese-factory.mixing :refer :all]
            [cheese-factory.units :refer :all]))

;; this bit was too complicated so made up junk

(defn make-matter [type]
  {:type type :kinetic-energy (rand-int 100)})

(defn make-acid [type]
  (assoc (make-matter [type :acid]) :ph 4))
;; ok so there are 1x10^26 h20 mols in here but that's not happening :)
(def water
  (make-matter :h2o))

(def whole-milk-liter
  (let [
        proteins {:atoms (take (grams 30) ( make-matter :casein))}
        milk-salts {:atoms  (take (grams 1) (make-matter :milk-salts))}
        carbs {:atoms (take (grams 51)
                            (cycle
                             (map #(make-matter %)
                                  [:lactose
                                   :glucose
                                   :galactose
                                   :other-oligosaccherides])))}
        lipids {:atoms (take (grams 22)
                             (cycle
                              (map #(make-acid %)
                                   [:palmitic
                                    :myristic
                                    :stearic
                                    :saturated-fatty
                                    :pentadecanoic
                                    :heptadecanoic
                                    :oleic
                                    :palmitoleic
                                    :linoleic
                                    :linolenic])))}
        solution (dissolve-in {:atoms (take (grams 931) water)} proteins milk-salts carbs)]
    (emulsify solution lipids)))

(def rennet-kilo
  (let [cow-stomach {:atoms (take (grams 0.7) (repeat (make-matter :enzyme)))}
        sodium-benzoate {:atoms (take (kilos 0.993) (repeat (make-matter :NaC7H5O2)))}]
    (dissolve-in sodium-benzoate cow-stomach)))
