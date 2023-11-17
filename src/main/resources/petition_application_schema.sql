-- SQL script for creating tables and adding constraints for the Petition application

-- Creating the petition table
CREATE TABLE petition (
    id BIGINT NOT NULL AUTO_INCREMENT,
    description VARCHAR(1500),
    title VARCHAR(255),
    PRIMARY KEY (id)
);;

-- Creating the signature table
CREATE TABLE signature (
    id BIGINT NOT NULL AUTO_INCREMENT,
    petition_id BIGINT,
    email VARCHAR(255),
    name VARCHAR(255),
    PRIMARY KEY (id)
);;

-- Creating the petition_signatures table
CREATE TABLE petition_signatures (
    petition_id BIGINT NOT NULL,
    signatures_id BIGINT NOT NULL
);;

-- Adding a unique constraint to the petition_signatures table
ALTER TABLE petition_signatures 
ADD CONSTRAINT unique_signatures_id UNIQUE (signatures_id);;

-- Adding foreign key constraints
ALTER TABLE petition_signatures 
ADD CONSTRAINT fk_signature_signatures_id FOREIGN KEY (signatures_id) REFERENCES signature (id);;

ALTER TABLE petition_signatures 
ADD CONSTRAINT fk_petition_petition_id FOREIGN KEY (petition_id) REFERENCES petition (id);;

ALTER TABLE signature 
ADD CONSTRAINT fk_petition_in_signature FOREIGN KEY (petition_id) REFERENCES petition (id);;




INSERT INTO petition (description, title)
VALUES
    ('Improve Public Transportation System', 'Public Transportation Enhancement Petition'),
    ('Renovate Local Community Center', 'Community Center Renovation Petition'),
    ('Improve Local Playground', 'Local Playground Improvement Petition'),
    ('Improve Local Library', 'Local Library Improvement Petition');

    
-- Add 2 dummy signatures for the first petition with different names and email addresses
INSERT INTO signature (petition_id, email, name)
SELECT
    1 AS petition_id,
    CONCAT('signature', s.id, '@example.com') AS email,
    CONCAT('John', s.id, ' Smith') AS name
FROM (
    SELECT ROW_NUMBER() OVER () AS id
    FROM information_schema.tables
    LIMIT 2
) AS s;;

-- Bind the generated signatures to the first petition in petition_signatures
INSERT INTO petition_signatures (petition_id, signatures_id)
SELECT
    1 AS petition_id,
    s.id AS signatures_id
FROM (
    SELECT id
    FROM signature
    WHERE petition_id = 1
    LIMIT 2
) AS s;;
 
