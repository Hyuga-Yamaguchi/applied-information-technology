use worldcup2014;

select group_name, min(ranking) as max_ranking, max(ranking) as min_ranking
from countries
group by group_name;
