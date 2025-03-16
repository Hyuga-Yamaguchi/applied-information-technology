# **列制約（Column Constraints）と表制約（Table Constraints）**

SQLにおける制約（Constraints）は、データベースの整合性を維持するために使用されます。制約は **「列制約」** と **「表制約」** に分類されます。

---

## **1. 列制約（Column Constraints）**

**列制約（Column Constraints）** は **個々の列に適用される制約** です。

テーブルの **`CREATE TABLE` 文で列を定義する際に直接適用** します。

### **主な列制約**

| 制約 | 説明 |
|------|------------------------------------------|
| `PRIMARY KEY` | 一意識別（NULL不可） |
| `UNIQUE` | 一意値を強制（NULLは許容） |
| `NOT NULL` | NULLを禁止 |
| `CHECK` | 条件を満たすデータのみ許可 |
| `DEFAULT` | デフォルト値を設定 |
| `REFERENCES`（外部キー） | 他のテーブルのキーを参照 |

---

### **列制約の例**

```sql
CREATE TABLE employees (
    employee_id INT PRIMARY KEY,  -- 主キー（NULL不可、一意）
    name VARCHAR(50) NOT NULL,    -- NULLを許可しない
    email VARCHAR(100) UNIQUE,    -- 重複不可（NULL可）
    age INT CHECK (age >= 18),    -- 18歳以上のみ許可
    department_id INT REFERENCES departments(department_id) -- 外部キー
);
```

- **`PRIMARY KEY` は `employee_id` の列レベルで適用**
- **`NOT NULL` で `name` にNULLを許可しない**
- **`UNIQUE` で `email` を一意にする（NULLは複数可）**
- **`CHECK` で `age >= 18` の制約を適用**
- **`REFERENCES` で `departments` テーブルの `department_id` を参照**

---

## **2. 表制約（Table Constraints）**

**表制約（Table Constraints）** は、**複数の列に対して適用される制約** です。
**テーブル定義の最後にまとめて記述** するのが特徴です。

### **主な表制約**

| 制約 | 説明 |
|------|-----------------------------------------|
| `PRIMARY KEY` | 複数列を主キーとして設定 |
| `UNIQUE` | 複数列の組み合わせで一意性を保証 |
| `CHECK` | 複数列を考慮した条件を設定 |
| `FOREIGN KEY` | 外部キーを設定 |

---

### **表制約の例**

```sql
CREATE TABLE employees (
    employee_id INT,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100),
    department_id INT,
    PRIMARY KEY (employee_id),  -- 主キー（表制約）
    UNIQUE (email),             -- 一意制約（表制約）
    FOREIGN KEY (department_id) REFERENCES departments(department_id) -- 外部キー
);
```

- **`PRIMARY KEY` や `UNIQUE` を表制約として記述**
- **表制約の `FOREIGN KEY` で `department_id` を `departments` の `department_id` に紐づける**

---

### **3. 列制約と表制約の違い**

| 比較 | 列制約 | 表制約 |
|------|------------------|------------------|
| **定義位置** | 各列の直後に記述 | `CREATE TABLE` の最後に記述 |
| **適用範囲** | 単一の列 | 複数の列 |
| **例** | `name VARCHAR(50) NOT NULL` | `PRIMARY KEY (employee_id, department_id)` |

---

### **4. 表制約が必要なケース**

#### **① 複合主キーを設定したい場合**

```sql
CREATE TABLE project_assignments (
    employee_id INT,
    project_id INT,
    PRIMARY KEY (employee_id, project_id)  -- 複数の列を主キーにする
);
```

- **`employee_id` と `project_id` の組み合わせで一意識別**

#### **② 複数の列を一意にしたい場合**

```sql
CREATE TABLE employees (
    employee_id INT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    UNIQUE (first_name, last_name) -- 姓名の組み合わせを一意にする
);
```

- **同じ `first_name` や `last_name` はOKだが、完全一致の名前は不可**

#### **③ `CHECK` で複数列を対象に条件を適用**

```sql
CREATE TABLE employees (
    employee_id INT PRIMARY KEY,
    age INT,
    salary INT,
    CHECK (salary >= age * 100) -- 年齢×100以上の給与を強制
);
```

- **複数の列の値を考慮してデータ制約を設定可能**

---

### **5. まとめ**

| 制約 | 列制約 | 表制約 |
|------|-------|-------|
| `PRIMARY KEY` | 1つの列を主キーにする | **複数の列を主キーにする** |
| `UNIQUE` | 1つの列を一意にする | **複数の列の組み合わせを一意にする** |
| `NOT NULL` | NULL を禁止する | - |
| `CHECK` | 1列の条件を制約 | **複数の列を考慮して条件を設定** |
| `FOREIGN KEY` | 他のテーブルの1列を参照 | **複数の列を使う外部キーを設定** |

- **単一列の制約は「列制約」**
- **複数列を対象にする場合は「表制約」**
- **主キーやユニーク制約を複数列で設定するなら表制約を使うのが一般的！**
