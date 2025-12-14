# Write your MySQL query statement below
SELECT
    a.visited_on,
    SUM(b.amount) AS amount,
    ROUND(SUM(b.amount) / 7, 2) AS average_amount
FROM
    (SELECT visited_on, SUM(amount) AS amount
     FROM Customer
     GROUP BY visited_on) a
JOIN
    (SELECT visited_on, SUM(amount) AS amount
     FROM Customer
     GROUP BY visited_on) b
ON
    b.visited_on BETWEEN DATE_SUB(a.visited_on, INTERVAL 6 DAY)
                     AND a.visited_on
GROUP BY
    a.visited_on
HAVING
    COUNT(b.visited_on) = 7
ORDER BY
    a.visited_on;
