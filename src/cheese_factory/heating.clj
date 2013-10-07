(ns cheese-factory.heating)

(defn jiggle-atom [atom]
  (update-in atom [:kinetic-energy] (fnil inc 0)))

(defn heat [matter]
  (update-in matter [:atoms] #(map jiggle-atom %)))

;; I choose to ignore the inaccuracy of this
(defn temp [{:keys [atoms]}]
  (let [ke (map :kinetic-energy atoms)]
    (/ (apply + ke) (count ke))))

(defn heat-to-temp [desired-temp target]
  (loop [target target]
    (if (>= (temp target) desired-temp)
      target
      (recur (heat target)))))
