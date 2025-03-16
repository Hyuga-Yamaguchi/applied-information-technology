use worldcup2014;

select (select c.name from countries c where c.id = p.country_id), avg(p.height) avg_height
from players p
group by country_id
order by avg_height desc;
