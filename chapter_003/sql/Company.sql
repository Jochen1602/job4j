CREATE TABLE company
(
id integer NOT NULL,
name character varying,
CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
id integer NOT NULL,
name character varying,
company_id integer,
CONSTRAINT person_pkey PRIMARY KEY (id)
);


INSERT INTO company (id, name) VALUES (1, 'First Company');
INSERT INTO company (id, name) VALUES (2, 'Second Company');
INSERT INTO company (id, name) VALUES (3, 'Third Company');
INSERT INTO company (id, name) VALUES (4, 'Fourth Company');
INSERT INTO company (id, name) VALUES (5, 'Fifth Company');
INSERT INTO company (id, name) VALUES (6, 'Sixth Company');

INSERT INTO person (id, name, company_id) VALUES (1, 'Alex', 1);
INSERT INTO person (id, name, company_id) VALUES (2, 'Bob', 1);
INSERT INTO person (id, name, company_id) VALUES (3, 'Charlez', 1);
INSERT INTO person (id, name, company_id) VALUES (4, 'Dimm', 2);
INSERT INTO person (id, name, company_id) VALUES (5, 'Even', 2);
INSERT INTO person (id, name, company_id) VALUES (6, 'Fedor', 2);
INSERT INTO person (id, name, company_id) VALUES (7, 'Greg', 2);
INSERT INTO person (id, name, company_id) VALUES (8, 'Hanna', 3);
INSERT INTO person (id, name, company_id) VALUES (9, 'Ivor', 3);
INSERT INTO person (id, name, company_id) VALUES (10, 'James', 4);
INSERT INTO person (id, name, company_id) VALUES (11, 'Kurfurst', 5);
INSERT INTO person (id, name, company_id) VALUES (12, 'Luna', 5);
INSERT INTO person (id, name, company_id) VALUES (13, 'Mann', 5);
INSERT INTO person (id, name, company_id) VALUES (14, 'Nebel', 5);
INSERT INTO person (id, name, company_id) VALUES (15, 'Oberst', 5);
INSERT INTO person (id, name, company_id) VALUES (16, 'Pfarel', 6);


// 1) Retrieve in a single query:
// - names of all persons that are NOT in the company with id = 5
// - company name for each person

SELECT p.name AS pm, c.name AS cn FROM person AS p LEFT JOIN company AS c ON (p.company_id = c.id) WHERE p.company_id <> 5;


// 2) Select the name of the company with the maximum number of persons + number of persons in this company

SELECT c.name AS cn, COUNT(p.company_id) AS co FROM person AS p LEFT JOIN company AS c ON (p.company_id = c.id) GROUP BY cn ORDER BY co DESC LIMIT 1;
