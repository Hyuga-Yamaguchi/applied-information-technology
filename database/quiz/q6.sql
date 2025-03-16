use worldcup2014;

select p.name player,
       p.position position,
       p.club club,
       (select count(*) from goals g where g.player_id = p.id) goals_num
from players p
order by goals_num desc
limit 20;
