use office;

SET FOREIGN_KEY_CHECKS = 0;

truncate table employees;
truncate table departments;

SET FOREIGN_KEY_CHECKS = 1;

insert into departments (department_id, department_name) value
(10, 'HR'),
(20, 'Engineering'),
(30, 'Sales');

insert into employees (employee_id, name, department_id, salary, email, hire_date) value
(1, 'Alice', 10, 5000, 'alice@example.com', '2018-06-01'),
(2, 'Bob', 20, 6000, 'bob@example.com', '2019-07-15'),
(3, 'Charlie', 10, 5500, 'charlie@example.com', '2020-03-20'),
(4, 'Dave', 30, 7000, 'dave@example.com', '2017-01-10'),
(5, 'Eve', 20, 6500, 'eve@example.com', '2021-10-05');
