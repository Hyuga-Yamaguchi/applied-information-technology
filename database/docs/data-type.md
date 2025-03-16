# **SQLのデータ型（Data Types）**

SQL では、データを適切に管理するために **データ型** を指定します。
データ型には主に以下の種類があります：

1. **数値型（INTEGER, DECIMAL など）**
2. **文字列型（CHAR, VARCHAR, TEXT など）**
3. **日付・時刻型（DATE, DATETIME, TIMESTAMP など）**
4. **ブール型（BOOLEAN）**
5. **バイナリ型（BLOB, VARBINARY など）**
6. **JSON型（MySQL, PostgreSQL など）**

---

## **1️⃣ 数値型（Numeric Data Types）**

数値型は、整数・小数・浮動小数点を扱うデータ型です。

| データ型 | 説明 | 格納範囲 |
|---------|------|---------|
| `TINYINT` | 小さな整数（1バイト） | `-128 ～ 127` (`UNSIGNED` なら `0 ～ 255`) |
| `SMALLINT` | 小さな整数（2バイト） | `-32,768 ～ 32,767` |
| `MEDIUMINT` | 中程度の整数（3バイト） | `-8,388,608 ～ 8,388,607` |
| `INT`（`INTEGER`） | 標準の整数（4バイト） | `-2,147,483,648 ～ 2,147,483,647` |
| `BIGINT` | 大きな整数（8バイト） | `-9,223,372,036,854,775,808 ～ 9,223,372,036,854,775,807` |
| `DECIMAL(p, s)`（`NUMERIC`） | 固定小数点（正確な計算向き） | `p` 桁のうち `s` 桁が小数部分 |
| `FLOAT` | 浮動小数点（4バイト） | 約 `±3.402823466E+38` |
| `DOUBLE`（`REAL`） | 浮動小数点（8バイト） | 約 `±1.7976931348623157E+308` |

**🔹 `UNSIGNED` を使うと負の値を許容しない**

```sql
CREATE TABLE users (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY
);
```

- `UNSIGNED` を使うと **`0` から始まる正の値のみ** になる

---

## **2️⃣ 文字列型（String Data Types）**

文字列データを格納するデータ型です。

| データ型 | 説明 | 最大長 |
|---------|------|------|
| `CHAR(n)` | 固定長の文字列 | `0 ～ 255` 文字 |
| `VARCHAR(n)` | 可変長の文字列 | `0 ～ 65,535` 文字（MySQL） |
| `TEXT` | 長い文字列（インデックスなし） | `65,535` 文字 |
| `TINYTEXT` | 小さなテキスト | `255` 文字 |
| `MEDIUMTEXT` | 中程度のテキスト | `16,777,215` 文字 |
| `LONGTEXT` | 超長いテキスト | `4,294,967,295` 文字 |

### **🔹 `CHAR` と `VARCHAR` の違い**

| データ型 | 格納方法 | 末尾の空白 | 使いどころ |
|---------|--------|------------|----------|
| `CHAR(n)` | **固定長**（常に `n` 文字） | 自動削除 | 国コード (`US`, `JP` など) |
| `VARCHAR(n)` | **可変長**（文字数分だけ格納） | 残る | 名前、メールアドレスなど |

```sql
CREATE TABLE employees (
    name VARCHAR(50),  -- 可変長
    country_code CHAR(2) -- 固定長
);
```

✅ **短くて固定長のデータなら `CHAR`、可変長なら `VARCHAR` を使う！**

---

## **3️⃣ 日付・時刻型（Date and Time Data Types）**

日付や時刻を格納するデータ型。

| データ型 | 説明 | 格納形式 |
|---------|------|---------|
| `DATE` | 年月日のみ | `YYYY-MM-DD` |
| `TIME` | 時間のみ | `HH:MM:SS` |
| `DATETIME` | 年月日 + 時間 | `YYYY-MM-DD HH:MM:SS` |
| `TIMESTAMP` | `UNIX` タイムスタンプ（UTC基準） | `YYYY-MM-DD HH:MM:SS` |

```sql
CREATE TABLE orders (
    order_id INT PRIMARY KEY,
    order_date DATETIME DEFAULT CURRENT_TIMESTAMP
);
```

✅ **`CURRENT_TIMESTAMP` を使うと自動的に現在の日時を挿入！**

---

## **4️⃣ ブール型（Boolean Data Types）**

| データ型 | 説明 |
|---------|------|
| `BOOLEAN` | `TRUE` または `FALSE`（MySQLでは `TINYINT(1)` として扱われる） |

```sql
CREATE TABLE users (
    is_active BOOLEAN DEFAULT TRUE
);
```

✅ **`TRUE` は `1`、`FALSE` は `0` として保存される**

---

## **5️⃣ バイナリ型（Binary Data Types）**

画像や動画、暗号化データなどを格納するためのデータ型。

| データ型 | 説明 | 最大長 |
|---------|------|------|
| `BLOB` | バイナリデータ | `65,535` バイト |
| `TINYBLOB` | 小さなバイナリデータ | `255` バイト |
| `MEDIUMBLOB` | 中程度のバイナリデータ | `16,777,215` バイト |
| `LONGBLOB` | 大きなバイナリデータ | `4,294,967,295` バイト |

```sql
CREATE TABLE images (
    id INT PRIMARY KEY,
    image_data BLOB
);
```

✅ **画像やファイルをデータベースに保存する際に使用（ただし、ストレージ容量に注意）**

---

## **6️⃣ JSON型（MySQL, PostgreSQL）**

| データ型 | 説明 |
|---------|------|
| `JSON` | JSON 形式のデータを格納（MySQL, PostgreSQL） |

```sql
CREATE TABLE products (
    id INT PRIMARY KEY,
    details JSON
);
```

✅ **JSON データをそのまま保存できる（MySQL, PostgreSQL でサポート）**

---

## **7️⃣ データ型の選び方**

| 用途 | 推奨データ型 | 例 |
|------|------------|----|
| **整数のID** | `INT` / `BIGINT` | `user_id` |
| **小数（価格など）** | `DECIMAL(p, s)` | `price DECIMAL(10,2)` |
| **短い固定長の文字** | `CHAR(n)` | `country_code CHAR(2)` |
| **可変長の文字列** | `VARCHAR(n)` | `email VARCHAR(100)` |
| **長い文章** | `TEXT` | `description TEXT` |
| **日付のみ** | `DATE` | `birthday DATE` |
| **日時** | `DATETIME` | `created_at DATETIME` |
| **真偽値** | `BOOLEAN` | `is_active BOOLEAN` |
| **バイナリデータ** | `BLOB` | `profile_image BLOB` |
| **JSONデータ** | `JSON` | `details JSON` |

---

## **✅ まとめ**

- **数値データ** → `INT`, `BIGINT`, `DECIMAL`, `FLOAT`
- **文字列データ** → `CHAR`, `VARCHAR`, `TEXT`
- **日付・時刻** → `DATE`, `DATETIME`, `TIMESTAMP`
- **ブール値** → `BOOLEAN`（`TINYINT(1)`）
- **バイナリデータ** → `BLOB`
- **JSONデータ** → `JSON`

**適切なデータ型を選ぶことで、データベースのパフォーマンスとストレージ効率を最適化できる！**
