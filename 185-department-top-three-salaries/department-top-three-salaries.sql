# Write your MySQL query statement below
select d.name as Department,
       e.name as Employee,
       e.salary as Salary
from (
    select *,
           DENSE_RANK() over(
               partition by  departmentId 
               order by salary desc
           ) as rnk
    from Employee
) e
JOIN Department d
     ON e.departmentId = d.id
WHERE e.rnk <= 3
ORDER BY d.name, e.salary DESC;
