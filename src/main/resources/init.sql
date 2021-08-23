create table developers (
	id serial primary key,
	first_name varchar(250) not null,
	last_name varchar (250) not null,
	age int not null,
	dev_sex varchar (100),
	comments text);


create table skills(
id serial primary key,
developer_id int not null
references developers(id),
brunch varchar(100) not null,
skill_level varchar(100) not null);

create table company(
id serial primary key,
name varchar(100) not null);

create table customers(
id serial primary key,
name varchar(100) not null);

create table projects(
id serial primary key,
name varchar(100) not null,
customer_id int not null
references customers(id),
company_id int not null
	references company(id),
	description text);

create table devs_in_project(
id serial primary key,
dev_id int not null references developers(id),
project_id int not null references projects(id));

alter table projects add column begin_date timestamp;

drop table skills;

create table brunch (
id serial primary key,
name varchar(250));

create table skill_level(
id serial primary key,
name varchar);

create table dev_skills(
id serial primary key,
dev_id int not null references developers(id),
skill_id int not null references brunch(id),
skill_level int not null references skill_level(id));

alter table developers add column salary int;

alter table projects add column cost int;
ALTER TABLE projects DROP COLUMN cost;

CREATE TABLE skills (
id integer primary key,
brunch varchar(50) not null,
stage varchar(50) not null);

CREATE TABLE developer_skills(
developer_id integer not null,
skill_id integer not null,
constraint fk_deelopers
	FOREIGN KEY (developer_id)
		REFERENCES developers(id),
CONSTRAINT fk_skills
	FOREIGN KEY (skill_id)
		REFERENCES skills(id));

DROP TABLE dev_skills;
DROP TABLE brunch;
DROP TABLE 	skill_level;