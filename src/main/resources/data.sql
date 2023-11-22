
INSERT INTO petition (description, title)
VALUES
    ('Improve Public Transportation System', 'Public Transportation Enhancement Petition'),
    ('Renovate Local Community Center', 'Community Center Renovation Petition'),
    ('Improve Local Playground', 'Local Playground Improvement Petition'),
    ('Improve Local Library', 'Local Library Improvement Petition');

    
-- Add 2 dummy signatures for the first petition with different names and email addresses
/* INSERT INTO signature (petition_id, email, name)
SELECT
    1 AS petition_id,
    CONCAT('signature', s.id, '@example.com') AS email,
    CONCAT('John', s.id, ' Smith') AS name
FROM (
    SELECT ROW_NUMBER() OVER () AS id
    FROM information_schema.tables
    LIMIT 2
) AS s; */

-- Bind the generated signatures to the first petition in petition_signatures
/* 
INSERT INTO petition_signatures (petition_id, signatures_id)
SELECT
    1 AS petition_id,
    s.id AS signatures_id
FROM (
    SELECT id
    FROM signature
    WHERE petition_id = 1
    LIMIT 2
) AS s; */
 
