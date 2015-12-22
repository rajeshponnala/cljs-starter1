(ns cljs-starter.app
  (:require [reagent.core :as reagent :refer [atom render]]
            [sablono.core :as sab]
            [devcards.core :as dc]
            [cljs.test :as t :refer [report] :include-macros true])
  (:require-macros [devcards.core :as dc :refer [defcard deftest]]
                   [cljs.test :refer [is testing async]]))

(enable-console-print!)

(defn init []
  #_(render  [:h1 "Hello, Clojurescript"]
             (.getElementById js/document "main-app-area"))
  (devcards.core/start-devcard-ui!))

(defcard first-card
  (sab/html  [:h "Hello Rajesh!"]))

(defcard {:this "is a map"})

(defcard text-box
  (sab/html [:input {:id "txtBox" :type "text"}])
  )
(defonce observed-atom
  (let [a (atom 0)]
    (js/setInterval (fn [] (swap! observed-atom inc)) 1000)
    a))

(defcard atom-observing-card observed-atom)

(defcard
  (fn [data-atom owner]
    (sab/html
     [:div
      [:h3 "Example: Counter: " (:count @data-atom)]
      [:button
       {:onClick (fn [] (swap! data-atom update-in [:count] inc))}
       "inc"]])))
