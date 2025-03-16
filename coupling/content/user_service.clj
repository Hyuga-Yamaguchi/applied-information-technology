(ns content.user-service)

(defn private-get-user
  [id]
  {:id id :name "Alice" :email "alice@example.com"})
