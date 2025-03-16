(ns stamp.send-email)

(defn send-email [data]
  (let [{:keys [email message]} data]
    (println "Sending email to:" email "with message:" message)))

(send-email {:id 1 :name "Alice" :email "alice@example.com" :message "Hello!"})

;; スタンプ結合: モジュールが大きなデータ構造を受け渡すが、その一部しか使用しない状態。
;; 不要なデータを渡す
;;
;; send-email は :email と :message しか使わないのに、大きなデータを渡している。
