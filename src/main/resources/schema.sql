-- SQL script for creating tables and adding constraints for Petition application

-- Creating the petition table
CREATE TABLE petition (
    id BIGINT NOT NULL AUTO_INCREMENT,
    description VARCHAR(1500),
    title VARCHAR(255),
    PRIMARY KEY (id)
);

-- Creating the signature table
CREATE TABLE signature (
   id BIGINT NOT NULL AUTO_INCREMENT,
   petition_id BIGINT,
   email VARCHAR(255),
   name VARCHAR(255),
   PRIMARY KEY (id),
   FOREIGN KEY (petition_id) REFERENCES petition (id)
);


ALTER TABLE signature 
ADD CONSTRAINT fk_petition_in_signature FOREIGN KEY (petition_id) REFERENCES petition (id);

INSERT INTO petition (description, title)
VALUES
    ('Improve Public Transportation System', 'Public Transportation Enhancement Petition'),
    ('Renovate Local Community Center', 'Community Center Renovation Petition'),
    ('Improve Local Playground', 'Local Playground Improvement Petition'),
    ('Improve Local Library', 'Local Library Improvement Petition');

INSERT INTO signature (petition_id, email, name)
VALUES
    (1, 'signature1@example.com', 'John Doe'),
    (1, 'signature2@example.com', 'Jane Smith');






