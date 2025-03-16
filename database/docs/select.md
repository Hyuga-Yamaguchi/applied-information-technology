# Select

```sql
select * from departments;
```

```text
+---------------+-----------------+
| department_id | department_name |
+---------------+-----------------+
|            10 | HR              |
|            20 | Engineering     |
|            30 | Sales           |
+---------------+-----------------+
```

```sql
select * from employees;
```

```text
+-------------+---------+---------------+--------+---------------------+------------+
| employee_id | name    | department_id | salary | email               | hire_date  |
+-------------+---------+---------------+--------+---------------------+------------+
|           1 | Alice   |            10 |   5000 | alice@example.com   | 2018-06-01 |
|           2 | Bob     |            20 |   6000 | bob@example.com     | 2019-07-15 |
|           3 | Charlie |            10 |   5500 | charlie@example.com | 2020-03-20 |
|           4 | Dave    |            30 |   7000 | dave@example.com    | 2017-01-10 |
|           5 | Eve     |            20 |   6500 | eve@example.com     | 2021-10-05 |
+-------------+---------+---------------+--------+---------------------+------------+
```

## 条件指定のwhere

```sql
select * from employees where salary > 6000;
```

```text
+-------------+------+---------------+--------+------------------+------------+
| employee_id | name | department_id | salary | email            | hire_date  |
+-------------+------+---------------+--------+------------------+------------+
|           4 | Dave |            30 |   7000 | dave@example.com | 2017-01-10 |
|           5 | Eve  |            20 |   6500 | eve@example.com  | 2021-10-05 |
+-------------+------+---------------+--------+------------------+------------+
```

## 集計のGROUP BY & HAVING

部署ごとの平均給与を取得

```sql
select department_id, AVG(salary)
from employees
group by department_id;
```

```text
+---------------+-------------+
| department_id | AVG(salary) |
+---------------+-------------+
|            10 |   5250.0000 |
|            20 |   6250.0000 |
|            30 |   7000.0000 |
+---------------+-------------+
```

平均給与が6000を超えている部署のみを取得

```sql
select department_id, AVG(salary) as avg_salary
from employees
group by department_id
having avg_salary > 6000;
```

```text
+---------------+------------+
| department_id | avg_salary |
+---------------+------------+
|            20 |  6250.0000 |
|            30 |  7000.0000 |
+---------------+------------+
```
