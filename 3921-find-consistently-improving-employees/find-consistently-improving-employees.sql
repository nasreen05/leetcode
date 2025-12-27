WITH ordered_reviews AS (
    SELECT
        employee_id,
        rating,
        review_date,
        ROW_NUMBER() OVER (
            PARTITION BY employee_id
            ORDER BY review_date DESC
        ) AS rn
    FROM performance_reviews
),

last_three AS (
    SELECT *
    FROM ordered_reviews
    WHERE rn <= 3
),

validated AS (
    SELECT
        employee_id,
        MAX(CASE WHEN rn = 3 THEN rating END) AS r1,
        MAX(CASE WHEN rn = 2 THEN rating END) AS r2,
        MAX(CASE WHEN rn = 1 THEN rating END) AS r3
    FROM last_three
    GROUP BY employee_id
    HAVING
        COUNT(*) = 3
        AND r1 < r2
        AND r2 < r3
)

SELECT
    e.employee_id,
    e.name,
    (v.r3 - v.r1) AS improvement_score
FROM validated v
JOIN employees e
    ON e.employee_id = v.employee_id
ORDER BY
    improvement_score DESC,
    e.name ASC;
