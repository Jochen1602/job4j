create table engine(
	id SERIAL PRIMARY KEY, 
	name VARCHAR(50) NOT NULL
);
create table transmission(
	id SERIAL PRIMARY KEY, 
	name VARCHAR(50) NOT NULL
);
create table body(
	id SERIAL PRIMARY KEY, 
	name VARCHAR(50) NOT NULL
);
create table cars(
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	engine_id INTEGER NOT NULL REFERENCES engine(id),
	transmission_id INTEGER NOT NULL REFERENCES transmission(id),
	body_id INTEGER NOT NULL REFERENCES body(id)
);

INSERT INTO engine(name) values('Maybach HL210');
INSERT INTO engine(name) values('Maybach HL295');
INSERT INTO engine(name) values('Maybach HL120');
INSERT INTO engine(name) values('V2');
INSERT INTO engine(name) values('Rolls-Royce Meteor');

INSERT INTO transmission(name) values('Type 1');
INSERT INTO transmission(name) values('Type 2');
INSERT INTO transmission(name) values('VK 1602');
INSERT INTO transmission(name) values('VK 3001 (D)');
INSERT INTO transmission(name) values('VK 3002 (D)');
INSERT INTO transmission(name) values('VK 4501');
INSERT INTO transmission(name) values('VK 4502');

INSERT INTO body(name) values('Kia Ceed');
INSERT INTO body(name) values('Skoda Octavia');
INSERT INTO body(name) values('Ford Focus');
INSERT INTO body(name) values('Toyota Camry');

INSERT INTO cars(name, engine_id, transmission_id, body_id) values('Ford Focus 3', 1, 1, 3);
INSERT INTO cars(name, engine_id, transmission_id, body_id) values('Ford Focus 2', 1, 2, 3);
INSERT INTO cars(name, engine_id, transmission_id, body_id) values('Ford Focus 1', 1, 3, 3);

INSERT INTO cars(name, engine_id, transmission_id, body_id) values('Kia Ceed SW', 5, 4, 1);
INSERT INTO cars(name, engine_id, transmission_id, body_id) values('Kia Pro Ceed', 5, 1, 1);
INSERT INTO cars(name, engine_id, transmission_id, body_id) values('Kia Ceed BiTurbo AMG', 4, 7, 1);

INSERT INTO cars(name, engine_id, transmission_id, body_id) values('Toyota Camry 2.5', 3, 2, 4);

--1. Вывести список всех машин и все привязанные к ним детали.
SELECT c.name, e.name, t.name, b.name FROM cars as c
INNER JOIN engine as e ON e.id = c.engine_id
INNER JOIN transmission as t ON t.id = c.transmission_id
INNER JOIN body as b ON b.id = c.body_id;

--2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
SELECT e.name FROM engine AS e LEFT OUTER JOIN cars as c ON c.engine_id = e.id WHERE c.id IS NULL;
SELECT t.name FROM transmission AS t LEFT OUTER JOIN cars as c ON c.transmission_id = t.id WHERE c.id IS NULL;
SELECT b.name FROM body AS b LEFT OUTER JOIN cars as c ON c.body_id = b.id WHERE c.id IS NULL;