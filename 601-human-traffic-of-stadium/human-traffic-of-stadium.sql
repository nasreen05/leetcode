# Write your MySQL query statement below
SELECT id, visit_date, people
FROM (
    SELECT *,
        LAG(people, 1) OVER (ORDER BY id) AS p1,
        LAG(people, 2) OVER (ORDER BY id) AS p2,
        LEAD(people, 1) OVER (ORDER BY id) AS n1,
        LEAD(people, 2) OVER (ORDER BY id) AS n2
    FROM Stadium
) AS t
WHERE 
    (people >= 100 AND p1 >= 100 AND p2 >= 100)
 OR (people >= 100 AND p1 >= 100 AND n1 >= 100)
 OR (people >= 100 AND n1 >= 100 AND n2 >= 100)
ORDER BY visit_date;
