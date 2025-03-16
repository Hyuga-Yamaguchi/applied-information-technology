(ns control.logging-service)

(defn log-message
  [level message]
  (case level
    :info (println "INFO:" message)
    :warn (println "WARN:" message)
    :error (println "ERROR:" message)))

;; 制御結合: あるモジュールが他のモジュールに制御フラグ（if 条件など）を渡す状態
;; フラグで動作を変更
;;
;; log-message の呼び出し側が :info :warn :error を意識する必要がある。
;; ログレベルが増えると、全モジュールの修正が必要になる。
