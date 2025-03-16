# `GROUP BY` の列指定に何を入れるべきか？

`GROUP BY` には、**「集約関数でない列」** を **すべて含める必要がある** というルールがあります。

---

## **1.  `GROUP BY` に指定すべき列**

### **基本ルール**

1. **`SELECT` 句にある「集約関数を使っていない列」は `GROUP BY` に含める必要がある**
2. **`SELECT` 句で集約関数を使っている列（`AVG()`, `SUM()`, `COUNT()` など）は `GROUP BY` に入れない**
3. **「グループの単位として適切なキー（主キーやユニークキー）」を `GROUP BY` に指定する**

---

## **2. 例: `GROUP BY` に何を入れるか**

### **テーブル: `players`**

| id | country_id | name   | height |
|----|-----------|--------|--------|
| 1  | 10        | Alice  | 170    |
| 2  | 10        | Bob    | 180    |
| 3  | 20        | Charlie | 175    |

### **正しい `GROUP BY` の例**

```sql
SELECT country_id, AVG(height) AS avg_height
FROM players
GROUP BY country_id;
```

- **`country_id` でグループ化**
- **`AVG(height)` は集約関数なので `GROUP BY` に含めない**
- **正しく動作する！**

| country_id | avg_height |
|------------|------------|
| 10         | 175.0      |
| 20         | 175.0      |

---

## **3. `GROUP BY` に間違った列を入れるとどうなる？**

### **❌ `GROUP BY` に `id` を入れるとグループ化できない**

```sql
SELECT id, country_id, AVG(height) AS avg_height
FROM players
GROUP BY id, country_id;
```

- **`id` は主キーなので、1行ずつグループ化される**
- **`AVG(height)` が意味をなさない**
- ❌ **間違った使い方**

---

### **4. `GROUP BY` に複数の列を入れるべきケース**

もし **国名も表示したい場合**：

```sql
SELECT c.id, c.name, AVG(p.height) AS avg_height
FROM countries c
INNER JOIN players p ON c.id = p.country_id
GROUP BY c.id, c.name;
```

- **`c.id` だけでも一意だが、`c.name` も `SELECT` にあるので `GROUP BY` に含めるのが安全**
- **他のデータベース（PostgreSQL など）でも互換性がある**

---

## **`GROUP BY` で指定する列の決め方**

| パターン | `GROUP BY` に入れるべき列 |
|----------|------------------------|
| **主キーがある場合** | 主キー (`id`) のみ |
| **特定のキーごとに集計** | そのキー (`country_id` など) |
| **`SELECT` で集約関数を使っていない列がある** | その列すべて (`c.id, c.name`) |

- **「どの単位でデータをまとめたいか？」を考えて `GROUP BY` を指定するとよい！** 🚀
