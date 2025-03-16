(ns external.db)

(defn fetch-user
  [id]
  {:id id :name "Alice" :email "alice@example.com"})
