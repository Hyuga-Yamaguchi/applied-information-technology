create database if not exists office;

use office;

drop table if exists employees;
drop table if exists departments;

create table departments (
    department_id int primary key,
    department_name varchar(50) not null
);

create table employees (
    employee_id int primary key,
    name varchar(50) not null,
    department_id int,
    salary int check (salary between 1 and 1000000),
    email varchar(50) unique,
    hire_date date,
    foreign key (department_id) references departments(department_id)
);

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
