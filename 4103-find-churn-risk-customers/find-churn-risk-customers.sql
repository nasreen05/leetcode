WITH ranked_events AS (
    SELECT
        *,
        ROW_NUMBER() OVER (
            PARTITION BY user_id
            ORDER BY event_date DESC, event_id DESC
        ) AS rn
    FROM subscription_events
),
user_summary AS (
    SELECT
        user_id,
        MIN(event_date) AS first_event_date,
        MAX(event_date) AS last_event_date,
        MAX(monthly_amount) AS max_historical_amount,
        SUM(event_type = 'downgrade') AS downgrade_count
    FROM subscription_events
    GROUP BY user_id
)
SELECT
    u.user_id,
    r.plan_name AS current_plan,
    r.monthly_amount AS current_monthly_amount,
    u.max_historical_amount,
    DATEDIFF(u.last_event_date, u.first_event_date) AS days_as_subscriber
FROM user_summary u
JOIN ranked_events r
  ON u.user_id = r.user_id
 AND r.rn = 1
WHERE
    r.event_type <> 'cancel'
    AND u.downgrade_count >= 1
    AND r.monthly_amount < 0.5 * u.max_historical_amount
    AND DATEDIFF(u.last_event_date, u.first_event_date) >= 60
ORDER BY
    days_as_subscriber DESC,
    u.user_id ASC;
