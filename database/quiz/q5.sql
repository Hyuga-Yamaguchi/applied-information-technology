use worldcup2014;

select pa.kickoff,
       (select c.name from countries c where pa.my_country_id = c.id) my,
       (select c.name from countries c where pa.enemy_country_id = c.id) enemy
from pairings pa
order by pa.kickoff
limit 35;

select pa.kickoff kickoff, c1.name my_country, c2.name enemy_country
from pairings pa
inner join countries c1 on pa.my_country_id = c1.id
inner join countries c2 on pa.enemy_country_id = c2.id
order by kickoff
limit 35;
