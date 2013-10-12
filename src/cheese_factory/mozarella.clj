(ns cheese-factory.mozarella
  (:require [cheese-factory.measurements :refer :all]
            [cheese-factory.mixing :refer :all]
            [cheese-factory.heating :refer :all]
            [cheese-factory.units :refer :all]
            ))

;; See http://www.thekitchn.com/how-to-make-homemade-mozzarella-cooking-lessons-from-the-kitchn-174355


(defn add-rennet-to-milk [{:keys [milk rennet] :as ingredients}]
  (-> ingredients
      (assoc-in [:milk-and-rennet] (stir milk rennet))
      (dissoc [:milk :rennet])))

(defn fetch-ingredients
  {:milk (pour-milk (cups 5/4))
   :rennet ()
   })


(defn make-cheese 
  [name
   region
   country
   aoc?
   pdo?
   doc?
   milk-origin
   milk-origin-subspecies
   pastuerized?
   aging-time
   mould-type] )

(make-cheese
 "Bleu de Gex" 
 "Jura" 
 "France"
 true 
 false 
 false 
 :cows 
 "Montbéliard" 
 false 
 (weeks 3) 
 "Penicillium glaucum")

(make-cheese 
 "Stilton" 
 ["Derbyshire" "Leicestershire" "Nottinghamshire"] 
 "United Kingdom" 
 false 
 true 
 false 
 :cows 
 "local" 
 true 
 (weeks 9) 
 "Penicillium roqueforti")

(defn calculate-olfactory-offence
  [{:keys [milk mould-type aging-time washing-solution]}]
  (let [pasteurization-factor (if (:is-pasteurized? milk) 0.5 1)]
    (* aging-time
       (+
        (milk-type->smell (:milk-type milk))
        pasteurization-factor
        (mould->smell mould-type)
        (washing-solution->smell washing-solution->smell)))))

(defn lactose-levels [{:keys [milk quantity]}]
  (if (:is-pastuerized? milk)
    (calculate-pastuerized-lactose-levels quantity (:milk-origin milk))))

(defn sell-by-date [{:keys [milk mould-type]}]
  (max (sell-by-date milk) (sell-by-date mould-type)))
