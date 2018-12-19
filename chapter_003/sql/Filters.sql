create table type(
	id SERIAL PRIMARY KEY, 
	name VARCHAR(20) NOT NULL
);
create table product(
	id SERIAL PRIMARY KEY, 
	name VARCHAR(20) NOT NULL, 
	type_id INTEGER NOT NULL, 
	expired_date DATE, 
	price MONEY,
	FOREIGN KEY (type_id) REFERENCES type(id)
);

INSERT INTO type values(0, 'СЫР');
INSERT INTO type values(1, 'МОЛОКО');
INSERT INTO type values(2, 'КОЛБАСА');
INSERT INTO type values(3, 'МАСЛО');
INSERT INTO type values(4, 'ХЛЕБ');
INSERT INTO type values(5, 'МОРОЖЕНОЕ');

INSERT INTO product values(0, 'ХЛЕБ БАТОН НАРЕЗНОЙ', 4, '2018-12-20', 20.00);
INSERT INTO product values(1, 'ХЛЕБ БАТОН ГЛАДК', 4, '2018-12-21', 30.00);
INSERT INTO product values(2, 'ХЛЕБЦЫ РЖАНЫЕ', 4, '2019-02-21', 50.00);
INSERT INTO product values(3, 'БУЛКА', 4, '2018-12-22', 25.00);
INSERT INTO product values(4, 'ХЛЕБ РЖАНОЙ', 4, '2018-12-19', 25.00);
INSERT INTO product values(5, 'ХЛЕБ РЖАНОЙ', 4, '2018-12-19', 25.00);

INSERT INTO product values(6, 'СЫР КОСТРОМСКОЙ', 0, '2019-01-10', 220.00);
INSERT INTO product values(7, 'СЫР ГАУДА', 0, '2019-01-10', 200.00);
INSERT INTO product values(8, 'МААСДАМ', 0, '2019-02-10', 300.00);
INSERT INTO product values(9, 'СЫР ПАРМЕЗАН', 0, '2019-01-30', 400.00);

INSERT INTO product values(10, 'ЧИСТАЯ ЛИНИЯ ПЛОМБИР', 5, '2019-01-22', 50.00);
INSERT INTO product values(11, 'ЧИСТАЯ ЛИНИЯ ЭСКИМО', 5, '2019-02-09', 60.00);
INSERT INTO product values(12, 'КОРОВКА ИЗ КОРОВЁНКИ', 5, '2019-02-02', 70.00);

INSERT INTO product values(13, 'МАСЛО ВКУСНОЕ', 3, '2019-03-10', 120.00);
INSERT INTO product values(14, 'МАСЛО ВКУСНОЕ', 3, '2019-03-10', 120.00);
INSERT INTO product values(15, 'МАСЛО ВКУСНОЕ', 3, '2019-03-10', 120.00);
INSERT INTO product values(16, 'МАСЛО ВКУСНОЕ', 3, '2019-03-10', 120.00);
INSERT INTO product values(17, 'МАСЛО ВКУСНОЕ', 3, '2019-03-10', 120.00);
INSERT INTO product values(18, 'МАСЛО ВКУСНОЕ', 3, '2019-03-10', 120.00);
INSERT INTO product values(19, 'МАСЛО ВКУСНОЕ', 3, '2019-04-10', 110.00);
INSERT INTO product values(20, 'МАСЛО ВКУСНОЕ', 3, '2019-04-10', 110.00);
INSERT INTO product values(21, 'МАСЛО ВКУСНОЕ', 3, '2019-04-10', 110.00);
INSERT INTO product values(22, 'МАСЛО ВКУСНОЕ', 3, '2019-04-10', 110.00);
INSERT INTO product values(23, 'МАСЛО ВКУСНОЕ', 3, '2019-04-10', 110.00);
INSERT INTO product values(24, 'МАСЛО ВКУСНОЕ', 3, '2019-04-10', 110.00);

INSERT INTO product values(25, 'МОЛОКО 1.0%', 1, '2018-12-30', 40.00);
INSERT INTO product values(26, 'МОЛОКО 2.5%', 1, '2018-12-30', 50.00);
INSERT INTO product values(27, 'МОЛОКО 3.2%', 1, '2018-12-30', 60.00);
INSERT INTO product values(28, 'МОЛОКО ЦЕЛЬНОЕ', 1, '2018-12-30', 70.00);
INSERT INTO product values(29, 'СЛИВКИ 20%', 1, '2019-01-25', 90.00);

INSERT INTO product values(30, 'МОРОЖЕНОЕ 33 КОРОВЫ', 5, '2019-01-22', 40.00);
INSERT INTO product values(31, 'МОРОЖЕНОЕ С РТУТЬЮ', 5, '2020-01-22', 10.00);

--1. Написать запрос получение всех продуктов с типом "СЫР"
SELECT * FROM product
WHERE type_id = 0;

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
SELECT * FROM product
WHERE name like '%МОРОЖЕНОЕ%';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT * FROM product
WHERE expired_date BETWEEN '2019-01-01' AND '2019-01-31';

--4. Написать запрос, который выводит самый дорогой продукт.
SELECT name, price FROM product
WHERE price = (SELECT MAX(price) FROM product);

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT type.name, count(product.name) FROM product, type
WHERE product.type_id = type.id
GROUP BY type.name;

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT * FROM product
WHERE type_id in (0, 1);

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
SELECT type.name, count(product.name) FROM product, type
WHERE product.type_id = type.id 
GROUP BY type.name
HAVING COUNT (product.name) < 10;

--8. Вывести все продукты и их тип.
SELECT r.name, p.name FROM product as r
inner join type as p on r.type_id = p.id;