(ns cheese-factory.mozarella
)

;; See http://www.thekitchn.com/how-to-make-homemade-mozzarella-cooking-lessons-from-the-kitchn-174355


(defn weeks [& x])

(defn milk-type->smell [& x])

(defn mould->smell {})

(defn make-cheese 
  [name
   region
   country
   aoc?
   pdo?
   doc?
   milk-origin
   milk-origin-subspecies
   pasteurized?
   aging-time
   mould-type]
  {:milk {:origin milk-origin
          :origin-subspecies milk-origin-subspecies
          :pasteurized? pasteurized?}
   :mould-type mould-type
   :region region
   :country country
   :aging-time aging-time})

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

(defn milk->smell [{:keys [milk-type is-pasteurized?]}]
  (+
   (if is-pasterurized? 0.5 1)
   (milk-type->smell milk-type)))

(defn lactose-levels [{:keys [milk-origin is-pasteurized?]}]
  (if is-pasteurized?
    (calculate-pasteurized-lactose-levels milk-origin)
    (calculate-pasteurized-lactose-levels milk-origin)))


(defn calculate-olfactory-offence
  [{:keys [milk mould-type aging-time washing-solution]}]
  (* aging-time
     (+
      (milk->smell milk)
      (mould->smell mould-type)
      (washing-solution->smell washing-solution->smell))))

(defn lactose-levels [{:keys [milk quantity]}]
  (if (:is-pastuerized? milk)
    (calculate-pastuerized-lactose-levels quantity (:milk-origin milk))))

(defn sell-by-date [{:keys [milk mould-type]}]
  (max (sell-by-date milk) (sell-by-date mould-type)))


(defmacro crap-fn [name ret]
  `(defn ~name [& x] (println name) ~ret))


(defn make-mozarella [citric-acid rennet milk]
  (zipmap [:curds :whey]
          (separate
           (stir-for (minutes 5)
                     (warm-to (farenheit 105)
                              (cut-into-squares
                               (leave-until-turned-into-curds 
                                (minutes 5)
                                (combine
                                 (dissolve-in-water rennet)
                                 (warm-to (farenheit 90) milk)))))))))


(defn make-mozarella [citric-acid rennet milk]
  (let [
        rennet-solution (dissolve-in-water rennet)
        warmed-milk (warm-to (farenheit 90) milk)]
    (->> [rennet-solution warmed-milk]
         (combine)
         (leave-until-turned-into-curds)
         (cut-into-squares)
         (warm-to (farenheit 105))
         (stir-for (minutes 5))
         (separate)
         (zipmap [:curds :whey]))))




(defn load-cheese-by-id [x] (throw (new Exception "Kablammo")))

(defn parse-cheese-ids [x] {:y 1})

(defn get-odour-level [x] {:x 2})

(defn cheese-smell-analyser [cheeses-to-assess]
  (->> cheeses-to-assess
       (map parse-cheese-ids)
       (map load-cheese-by-id)
       (map #(select-keys % [:mould-type :aging-time]))
       (map get-odour-level)
       (reduce #(merge-with max %1 %2) {})))

(defn parse-cheese-ids [& x ] (load-cheese-by-id x))
(defn get-odour-level [& x] 1)
(defn calculate-stats [x])

(defn cheese-smell-analyser-2 [cheeses-to-assess]
  (->> cheeses-to-assess
       (load-cheeses)
       (map parse-cheese-ids)
       (map get-odour-level)
       (reduce calculate-stats)))
