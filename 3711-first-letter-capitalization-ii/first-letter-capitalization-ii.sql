WITH RECURSIVE words AS (
    SELECT 
        content_id,
        content_text,
        1 AS pos,
        SUBSTRING_INDEX(content_text, ' ', 1) AS word,
        SUBSTRING(content_text, LENGTH(SUBSTRING_INDEX(content_text, ' ', 1)) + 2) AS rest
    FROM user_content

    UNION ALL

    SELECT
        content_id,
        content_text,
        pos + 1,
        SUBSTRING_INDEX(rest, ' ', 1),
        SUBSTRING(rest, LENGTH(SUBSTRING_INDEX(rest, ' ', 1)) + 2)
    FROM words
    WHERE rest <> ''
),

capitalized_words AS (
    SELECT
        content_id,
        pos,
        CASE
            WHEN word LIKE '%-%' THEN
                CONCAT(
                    UPPER(LEFT(SUBSTRING_INDEX(word, '-', 1), 1)),
                    LOWER(SUBSTRING(SUBSTRING_INDEX(word, '-', 1), 2)),
                    '-',
                    UPPER(LEFT(SUBSTRING_INDEX(word, '-', -1), 1)),
                    LOWER(SUBSTRING(SUBSTRING_INDEX(word, '-', -1), 2))
                )
            ELSE
                CONCAT(
                    UPPER(LEFT(word, 1)),
                    LOWER(SUBSTRING(word, 2))
                )
        END AS fixed_word
    FROM words
)

SELECT
    uc.content_id,
    uc.content_text AS original_text,
    GROUP_CONCAT(cw.fixed_word ORDER BY cw.pos SEPARATOR ' ') AS converted_text
FROM user_content uc
JOIN capitalized_words cw
    ON uc.content_id = cw.content_id
GROUP BY uc.content_id, uc.content_text;
