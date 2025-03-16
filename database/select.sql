-- 全従業員を取得

use office;

-- select * from employees;

-- select * from departments;

-- select * from employees where salary > 6000;

-- select department_id, AVG(salary)
-- from employees
-- group by department_id;

select department_id, AVG(salary) as avg_salary
from employees
group by department_id
having avg_salary > 6000;
