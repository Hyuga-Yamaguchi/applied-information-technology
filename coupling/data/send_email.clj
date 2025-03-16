(ns data.send-email)

(defn send-email [email message]
  (println "Sending email to:" email "with message:" message))

(send-email "alice@example.com" "Hello!")

;; データ結合: モジュールが必要なデータだけを渡す状態（最も理想的な結合）。
;; 理想的なデータ結合
;;
;; 変更に強い。
;; どのデータが使われるか明確。
