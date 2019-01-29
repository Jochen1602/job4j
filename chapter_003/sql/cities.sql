CREATE TABLE cities
(
	id SERIAL PRIMARY KEY,
	name VARCHAR(20) NOT NULL
);

INSERT INTO cities (name) VALUES ('Москва');
INSERT INTO cities (name) VALUES ('СПб');
INSERT INTO cities (name) VALUES ('СПб');
INSERT INTO cities (name) VALUES ('Москва');
INSERT INTO cities (name) VALUES ('Москва');
INSERT INTO cities (name) VALUES ('Москва');
INSERT INTO cities (name) VALUES ('Казань');
INSERT INTO cities (name) VALUES ('Казань');
INSERT INTO cities (name) VALUES ('Казань');
INSERT INTO cities (name) VALUES ('Москва');

CREATE TEMPORARY TABLE temp AS
(
	SELECT MIN (id) AS id
	FROM cities
	GROUP BY name
);

DELETE FROM cities
WHERE cities.id NOT IN (SELECT id FROM temp);

DELETE FROM cities
WHERE id NOT IN (SELECT MIN(id) FROM cities GROUP BY name HAVING COUNT(id) > 1);

--В системе есть таблица cities. с полями id, name.
--Система парсит объявления и записывывать города.
--В коде системы оказался баг. Он записывал дубликаты городов.
--Москва, Москва, СПб, Казань.
--Багу поправили на уровне приложения, но таблица все равно содержит дубликаты.
--Ваша задача написать sql скрипт, который оставит в таблице только уникальные города. Если было три раза написана Москва. то нужно оставить только одну Москву.