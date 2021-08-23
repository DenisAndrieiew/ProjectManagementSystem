
insert into public.developers
(first_name, last_name, age, dev_sex, comments)
values
('Denis', 'Andrieiew', 36, 'male', null),
('Anton', 'Romanov', 29, 'male', 'fiction character');
insert into public.developers
(first_name, last_name, age, dev_sex, comments)
values
('Sveta', 'SV', 20, 'female', 'fiction character'),
('Marat', 'Lost', 45, 'male', 'fiction character');
insert into brunch(id, name) values
(1, 'Java'),
(2, 'C#'),
(3, 'JS'),
(4, 'C++'),
(5, 'PHP');

insert into skill_level (id, name) values
(1, 'Junior'),
(2, 'Middle'),
(3, 'Senior');

insert into dev_skills(dev_id, skill_id, skill_level)
values
(1, 1, 1),
(2, 2, 2),
(2, 1, 1),
(3, 1, 3),
(3, 3, 2),
(3, 4, 2),
(4, 2, 1),
(4, 3, 2);

insert into public.company
(name)
values
('GoIt'),
('Umbrella'),
('Vault Tec.');


insert into public.customers (name) values
('FUIB'),
('Vault'),
('Temple of Greate Justice'),
('Ministry of Truth'),
('Bruce Wayne');

insert into projects (name, company_id, customer_id) values
('FUIB online', (select (id) from company where name = 'GoIt'), (select (id) from customers where name = 'FUIB')),
('FUIB mobile', (select (id) from company where name = 'GoIt'),(select (id) from customers where name = 'FUIB')),
('Fallout', (select (id) from company where name = 'Vault Tec.'),(select (id) from customers where name = 'Vault')),
('Long Life Research', (select (id) from company where name = 'Umbrella'),(select (id) from customers where name = 'Temple of Greate Justice')),
('Batmobile autopilot 2.0', (select (id) from company where name = 'GoIt'),(select (id) from customers where name = 'Bruce Wayne' )),
('"New world eyes"', (select (id) from company where name = 'Vault Tec.'), (select (id) from customers where name = 'Ministry of Truth'));

insert into devs_in_project (dev_id, project_id) values
(1, 1),
(1,2),
(2,4),
(2,6),
(3,5),
(4,6);

update projects set begin_date='10.01.1998' where id =1;
update projects set begin_date='10.11.2018' where id =2;
update projects set begin_date='07.07.2001' where id =3;
update projects set begin_date='08.12.2015' where id =4;
update projects set begin_date='31.12.2019' where id =5;
update projects set begin_date='03.05.2021' where id =6;

update developers set salary = 800 where id = 1;
update developers set salary = 1200 where id = 2;
update developers set salary = 3000 where id = 3;
update developers set salary = 1500 where id = 4;


update projects set cost = 800 where id = 1;
update projects set cost = 800 where id = 2;
update projects set cost = 0 where id = 3;
update projects set cost = 1200 where id = 4;
update projects set cost = 3000 where id = 5;
update projects set cost = 2700 where id = 6;

UPDATE developers SET dev_sex ='MALE' WHERE dev_sex = 'male';
UPDATE developers SET dev_sex ='FEMALE' WHERE dev_sex = 'female';

UPDATE brunch SET name = 'JAVA' WHERE name = 'Java';
UPDATE brunch SET name = 'C_SHARP' WHERE name = 'C#';
UPDATE brunch SET name = 'JAVA_SCRIPT' WHERE name = 'JS';
UPDATE brunch SET name = 'CPP' WHERE name = 'C++';

UPDATE skill_level SET name = 'JUNIOR' WHERE name ='Junior';
UPDATE skill_level SET name = 'MIDDLE' WHERE name ='Middle';
UPDATE skill_level SET name = 'SENIOR' WHERE name ='Senior';

INSERT INTO skills (id, brunch, stage)
    VALUES (1, 'JAVA', 'JUNIOR'),
    	   (2, 'JAVA', 'MIDDLE'),
    	   (3, 'JAVA', 'SENIOR'),
    	   (4, 'C_SHARP', 'JUNIOR'),
    	   (5, 'C_SHARP', 'MIDDLE'),
    	   (6, 'C_SHARP', 'SENIOR'),
    	   (7, 'JAVA_SCRIPT', 'JUNIOR'),
    	   (8, 'JAVA_SCRIPT', 'MIDDLE'),
    	   (9, 'JAVA_SCRIPT', 'SENIOR'),
    	   (10, 'CPP', 'JUNIOR'),
    	   (11, 'CPP', 'MIDDLE'),
    	   (12, 'CPP', 'SENIOR'),
    	   (13, 'PHP', 'JUNIOR'),
    	   (14, 'PHP', 'MIDDLE'),
    	   (15, 'PHP', 'SENIOR');