use worldcup2014;

select c.name, avg(p.height) p_height
from countries c
inner join players p
on c.id = p.country_id
group by c.id, c.name
order by p_height desc;
