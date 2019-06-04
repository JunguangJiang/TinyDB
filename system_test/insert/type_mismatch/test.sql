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
-- int -> float --
insert into T values (1.0, 1, 1.0, 1.0, "1");
insert into T(m_int, m_long, m_string) values (11.0, 11, "11");
select * from T;

-- int -> string --
insert into T values ("2", 2, 2.0, 2.0, "2");
insert into T(m_int, m_long, m_string) values ("12", 12, "12");
select * from T;

-- long -> float --
insert into T values (3, 3.0, 3.0, 3.0, "3");
insert into T(m_int, m_long, m_double, m_string) values (13, 13.0, 13.0, "13");
select * from T;

-- long -> string --
insert into T values (4, "4", 4.0, 4.0, "4");
insert into T(m_int, m_long, m_double, m_string) values (14, "14", 14.0, "14");
select * from T;

-- float -> int --
insert into T values (5, 5, 5, 5.0, "5");
insert into T(m_int, m_long, m_float, m_string) values (15, 15, 15, "15");
select * from T;

-- float -> string --
insert into T values (6, 6, "6", 6.0, "6");
insert into T(m_int, m_long, m_float, m_string) values (16, 16, "16", "16");
select * from T;

-- double -> int --
insert into T values (7, 7, 7.0, 7, "7");
insert into T(m_int, m_long, m_double, m_string) values (17, 17, 17, "17");
select * from T;

-- double -> string --
insert into T values (8, 8, 8.0, "8", "8");
insert into T(m_int, m_long, m_double, m_string) values (18, 18, "18", "18");
select * from T;

-- string -> int --
insert into T values (9, 9, 9.0, 9.0, 9);
insert into T(m_int, m_long, m_string) values (19, 19, 19);
select * from T;

-- string -> float --
insert into T values (10, 10, 10.0, 10.0, 10.0);
insert into T(m_int, m_long, m_float, m_string) values (20, 20, 20.0, 20.0);
select * from T;


-- drop database --
drop database test;
show databases;