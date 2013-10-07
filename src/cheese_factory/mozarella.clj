(ns cheese-factory.mozarella
  (:require [cheese-factory.measurements :refer :all]
            [cheese-factory.mixing :refer :all]
            [cheese-factory.heating :refer :all]
            [cheese-factory.units :refer :all]
            ))

;; See http://www.thekitchn.com/how-to-make-homemade-mozzarella-cooking-lessons-from-the-kitchn-174355

(defn prepare-citric-acid [{:keys [citric-acid]}]
  (dissolve-in (water-measure 1 :cup) citric-acid))

(defn prepare-rennet [ingredients]
  (update-in ingredients [:rennet]
             #(dissolve-in (water-measure 1/4 :cup) %)))

(defn warm-milk [ingredients pot]
  (update-in ingredients [:milk]
             (comp
              (pour-into pot)
              (heat-to-temp (farenheit 90)))))

(defn add-rennet-to-milk [{:keys [milk rennet] :as ingredients}]
  (-> ingredients
      (assoc-in [:milk-and-rennet] (stir milk rennet))
      (dissoc [:milk :rennet])))
