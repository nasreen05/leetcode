# Write your MySQL query statement below
select  id, num
from (select id, COUNT(*) as num,
           DENSE_RANK() over (order by COUNT(*) desc) as rnk
    from (
        select requester_id as id from RequestAccepted
        union all
        select accepter_id as id from RequestAccepted
    ) as all_friends
    group by id
) as ranked
where rnk = 1;
