(ns content.email-service
  (:require [content.user-service]))

(defn send-email
  [user-id message]
  (let [user-data (content.user-service/private-get-user user-id)]
    (println "Sending email to:" (:email user-data) "with message:" message)))


;; 内部結合: あるモジュールが他のモジュールの内部実装に直接依存している
;; 他の名前空間の内部関数に直接アクセス
;;
;; private-get-useはuser-serviceで使われるべきもの
;; user-serviceの実装が変わるとemail-serviceに影響が出る
