(ns common.email-service
  (:require [common.shared-state]))

(defn send-email
  [user-id message]
  (let [user-data (@common.shared-state/users user-id)]
    (println "Sending email to:" (:email user-data) "with message:" message)))


;; 共通結合: 複数のモジュールが複数のグローバルなデータを共有する状態
;; グローバルなatomを共有
;;
;; shared-state/usersに依存するため、どのモジュールがデータを変更するのか不明確
;; 予期せぬ変更が起こるかも
