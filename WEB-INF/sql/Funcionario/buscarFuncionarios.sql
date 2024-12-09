Select funcionarioaid as id, u.fullname
from funcionarios f
join usuario u on u.usuarioid = f.usuarioid
order by funcionarioid asc;