# Write your MySQL query statement below
WITH invalid_logs AS (
    SELECT
        ip
    FROM logs
    WHERE
        -- Condition 1: not exactly 4 octets
        (LENGTH(ip) - LENGTH(REPLACE(ip, '.', ''))) <> 3
        
        OR
        -- Condition 2: leading zeros in any octet
        ip REGEXP '(^|\\.)(0[0-9]+)'
        
        OR
        -- Condition 3: any octet > 255
        EXISTS (
            SELECT 1
            FROM (
                SELECT
                    CAST(SUBSTRING_INDEX(SUBSTRING_INDEX(ip, '.', n), '.', -1) AS UNSIGNED) AS octet
                FROM (
                    SELECT 1 n UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4
                ) nums
            ) t
            WHERE octet > 255
        )
)

SELECT
    ip,
    COUNT(*) AS invalid_count
FROM invalid_logs
GROUP BY ip
ORDER BY invalid_count DESC, ip DESC;
