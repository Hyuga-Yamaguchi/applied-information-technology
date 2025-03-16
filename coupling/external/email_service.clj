(ns external.email-service
  (:require [external.db]))

(defn send-email
  [user-id message]
  (let [user-data (external.db/fetch-user user-id)]
    (println "Sending email to:" (:email user-data) "with message:" message)))


;; 外部結合: モジュールが外部環境に依存する
;; 外部DBに依存
;;
;; fetch-userが変わるとsend-emailも変更が必要
;; テストしにくい
