create database test;
use database test;
create table T(
m_int int not null,
m_long long,
m_float float,
m_double double,
m_string string(5) not null,
PRIMARY KEY (m_long)
);
show databases;
select * from T;
-- a database exist --


-- select --
-- not insert --
insert into T(m_long, m_float, m_double, m_string) values (30, 30.0, 30.0, "30");
select *from T;
insert into T(m_int, m_float, m_double, m_string) values (30, 30.0, 30.0, "30");
select *from T;
insert into T(m_int, m_long, m_double, m_string) values (30, 30, 30.0, "30");
select *from T;
insert into T(m_int, m_long, m_float, m_string) values (30, 31, 30.0, "30");
select *from T;
insert into T(m_int, m_long, m_float, m_double) values (30, 32, 30.0, 30.0);
select *from T;
-- insert all null --
insert into T(m_long) values (null);
select *from T;


-- drop database --
drop database test;
show databases;

create database test;
use database test;
create table T(
m_int int,
m_long long,
m_float float,
m_double double,
m_string string(5),
PRIMARY KEY (m_long)
);
show databases;
select * from T;

-- not insert --
insert into T(m_long, m_float, m_double, m_string) values (30, 30.0, 30.0, "30");
select *from T;
insert into T(m_int, m_float, m_double, m_string) values (30, 30.0, 30.0, "30");
select *from T;
insert into T(m_int, m_long, m_double, m_string) values (30, 30, 30.0, "30");
select *from T;
insert into T(m_int, m_long, m_float, m_string) values (30, 31, 30.0, "30");
select *from T;
insert into T(m_int, m_long, m_float, m_double) values (30, 32, 30.0, 30.0);
select *from T;
-- insert all null --
insert into T(m_long) values (null);
select *from T;


-- drop database --
drop database test;
show databases;