(ns cheese-factory.mixing)

(defn dissolve-in [solution & solutes]
  (let [stirred (shuffle (flatten (map :atoms solutes)))]
    (update-in solution [:atoms] #(shuffle (concat  % stirred)))))

(defn pour-into [container substance]
  (assoc-in substance :container container))

(defn combine [granularity matter]
  (let [chunk-size (apply min granularity (map count (:atoms matter)))]
    {:atoms
     (flatten (shuffle (mapcat #(partition chunk-size %) (map :atoms matter))))}))

(defn emulsify [& liquids]
  (combine 5 liquids))

(defn stir [& stuff]
  (combine 50 stuff))

(defn make-compound [molecules]
  {:kinetic-energy (apply + (map :kinetic-energy molecules))
   :type (map :type molecules)})
