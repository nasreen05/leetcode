# Write your MySQL query statement below
SELECT
    customer_id
FROM customer_transactions
GROUP BY customer_id
HAVING
    -- At least 3 purchase transactions
    SUM(CASE WHEN transaction_type = 'purchase' THEN 1 ELSE 0 END) >= 3
    
    -- Active for at least 30 days
    AND DATEDIFF(MAX(transaction_date), MIN(transaction_date)) >= 30
    
    -- Refund rate < 20%
    AND 
    (
        SUM(CASE WHEN transaction_type = 'refund' THEN 1 ELSE 0 END) * 1.0
        /
        COUNT(*)
    ) < 0.20
ORDER BY customer_id;
