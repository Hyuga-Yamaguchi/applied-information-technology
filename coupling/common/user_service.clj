(ns common.user-service
  (:require [common.shared-state]))

(defn add-user
  [id name email]
  (swap! common.shared-state/users assoc id {:name name :email email}))
