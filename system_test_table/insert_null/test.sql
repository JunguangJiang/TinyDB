create database test;
use database test;
create table T(
m_int int not null,
m_long long,
m_float float,
m_double double,
m_string string(5) not null
);
show databases;
select * from T;
-- a database exist --

-- select --
-- insert null --
insert into T values (null, 0, 0.0, 0.0, "0");
select * from T;
insert into T values (0, null, 0.0, 0.0, "0");
select * from T;
insert into T values (0, 0, null, 0.0, "0");
select * from T;
insert into T values (0, 0, 0.0, null, "0");
select * from T;
insert into T values (0, 0, 0.0, 0.0, null);
select * from T;
-- insert all null --
insert into T values (null, null, null, null, null);
select * from T;

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
m_string string(5)
);
show databases;
select * from T;
-- a database exist --

-- select --
-- insert null --
insert into T values (null, 0, 0.0, 0.0, "0");
select * from T;
insert into T values (0, null, 0.0, 0.0, "0");
select * from T;
insert into T values (0, 0, null, 0.0, "0");
select * from T;
insert into T values (0, 0, 0.0, null, "0");
select * from T;
insert into T values (0, 0, 0.0, 0.0, null);
select * from T;
-- insert all null --
insert into T values (null, null, null, null, null);
select * from T;

-- drop database --
drop database test;
show databases;