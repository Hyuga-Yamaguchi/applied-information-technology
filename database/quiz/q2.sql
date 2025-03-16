use worldcup2014;

select avg(height) avg_height, avg(weight) avg_weight
from players
where position = 'GK';
