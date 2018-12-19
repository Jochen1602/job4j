CREATE DATABASE tracker;

create table users (
	user_id SERIAL PRIMARY KEY,
	name VARCHAR(200)
);
create table roles (
	role_id SERIAL PRIMARY KEY,
	name VARCHAR(2000)
);
create table role_rules (
	role_rule_id SERIAL PRIMARY KEY,
	name VARCHAR(2000)
);
create table item_states (
	state_id SERIAL PRIMARY KEY,
	name VARCHAR(50)
);
create table item_categories (
	category_id SERIAL PRIMARY KEY,
	name VARCHAR(200)
);

create table items (
	id SERIAL PRIMARY KEY,
	category INTEGER,
	state INTEGER,
	user_id INTEGER,
	FOREIGN KEY (category) REFERENCES item_categories (category_id),
	FOREIGN KEY (state) REFERENCES item_states (state_id),
	FOREIGN KEY (user_id) REFERENCES users (user_id),
	info VARCHAR(2000)
);
create table _comments (
	comment_id SERIAL PRIMARY KEY,
	item_id_ INTEGER,
	FOREIGN KEY (item_id_) REFERENCES items (id),
	name VARCHAR(2000)
);
create table attaches (
	attach_id SERIAL PRIMARY KEY,
	item_id INTEGER,
	FOREIGN KEY (item_id) REFERENCES items (id),
	name VARCHAR(2000)
);
create table role_rules_of_role (
	id SERIAL PRIMARY KEY,
	roles_id INTEGER,
	role_rules_id INTEGER,
	FOREIGN KEY (roles_id) REFERENCES roles (role_id),
	FOREIGN KEY (role_rules_id) REFERENCES role_rules (role_rule_id)
);
--many-to-one (у юзера несколько ролей)
create table roles_of_user (
	rou_id SERIAL PRIMARY KEY,
	user_id INTEGER,
	role_id INTEGER,
	FOREIGN KEY (user_id) REFERENCES users (user_id),
	FOREIGN KEY (role_id) REFERENCES roles (role_id)
);
----entering base data----
insert into roles(role_id, name) values(0, 'read');
insert into roles(role_id, name) values(1, 'write');
insert into roles(role_id, name) values(2, 'delete');
insert into role_rules(role_rule_id, name) values(0, 'Can only read');
insert into role_rules(role_rule_id, name) values(1, 'Can only write');
insert into role_rules(role_rule_id, name) values(2, 'Can only delete');
insert into users(user_id, name) values(0, 'Alf Alf');
insert into users(user_id, name) values(1, 'Bob Bob');
insert into users(user_id, name) values(2, 'Chad Chad');
INSERT INTO item_states VALUES(0, 'just posted');
INSERT INTO item_states VALUES(1, 'in work');
INSERT INTO item_states VALUES(2, 'finished');
INSERT INTO item_categories VALUES(0, 'roles problem');
INSERT INTO item_categories VALUES(1, 'users problem');
INSERT INTO item_categories VALUES(2, 'another problem');
INSERT INTO items VALUES(0, 0, 0, 0, 'first blood');
INSERT INTO items VALUES(1, 2, 1, 1, 'well done');
INSERT INTO items VALUES(2, 1, 1, 2, 'fire in the hole');
insert into _comments(comment_id, item_id_, name) values(0, 2, '1111');
insert into _comments(comment_id, item_id_, name) values(1, 2, '1111111');
insert into _comments(comment_id, item_id_, name) values(2, 1, '11112222');
insert into _comments(comment_id, item_id_, name) values(3, 1, '22111122');
insert into _comments(comment_id, item_id_, name) values(4, 1, '22221111');
insert into attaches(attach_id, item_id, name) values(0, 1, 'file 1');
insert into attaches(attach_id, item_id, name) values(1, 1, 'file 2');
insert into attaches(attach_id, item_id, name) values(2, 0, 'file 11');
insert into attaches(attach_id, item_id, name) values(3, 0, 'file 12');
insert into role_rules_of_role(id, roles_id, role_rules_id) values(0, 0, 0);
insert into role_rules_of_role(id, roles_id, role_rules_id) values(1, 1, 1);
insert into role_rules_of_role(id, roles_id, role_rules_id) values(2, 2, 2);
insert into roles_of_user(rou_id, user_id, role_id) values(0, 0, 0);
insert into roles_of_user(rou_id, user_id, role_id) values(1, 2, 2);
insert into roles_of_user(rou_id, user_id, role_id) values(2, 1, 1);