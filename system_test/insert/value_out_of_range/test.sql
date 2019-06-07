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
-- int out of range --
insert into T values (2147483648, 1, 1.0, 1.0, "1");
select * from T;
insert into T values (-2147483649, 11, 11.0, 11.0, "11");
select * from T;
insert into T(m_int, m_long, m_string) values (2147483648, 21, "21");
select * from T;
insert into T(m_int, m_long, m_string) values (-2147483649, 31, "31");
select * from T;

-- long out of range --
-- -9223372036854774808~9223372036854774807 --
insert into T values (2, 9223372036854775808, 2.0, 2.0, "2");
select * from T;
insert into T values (22, -9223372036854775809, 22.0, 22.0, "22");
select * from T;
insert into T(m_int, m_long, m_string) values (32, 9223372036854775808, "32");
select * from T;
insert into T(m_int, m_long, m_string) values (42, -9223372036854775809, "42");
select * from T;

-- float out of range --
-- 3.402823e+38 ~ 1.401298e-45 --

-- -- max +, - --
-- insert into T values (3, 3, 3.402824e+38, 3.0, "3");
-- select * from T;
-- insert into T values (13, 13, -3.402823e+39, 13.0, "13");
-- select * from T;
-- -- min +, -  --
-- insert into T values (23, 23, 1.401298e-46, 23.0, "23");
-- select * from T;
-- insert into T values (33, 33, -1.401298e-46, 33.0, "33");
-- select * from T;
-- insert into T(m_int, m_long, m_float, m_string) values (43, 43, 3.402824e+38, "43");
-- select * from T;
-- insert into T(m_int, m_long, m_float, m_string) values (53, 53, -3.402823e+39, "53");
-- select * from T;
-- insert into T(m_int, m_long, m_float, m_string) values (63, 63, 1.401298e-46, "63");
-- select * from T;
-- insert into T(m_int, m_long, m_float, m_string) values (73, 73, -1.401298e-46, "73");
-- select * from T;
--
-- -- double out of range --
-- -- 1.797693e+308~ 4.9000000e-324  --
-- insert into T values (4, 4, 4.0, 1.797694e+308, "4");
-- select * from T;
-- insert into T values (14, 14, 14.0, -1.797694e+308, "14");
-- select * from T;
-- insert into T values (24, 24, 24.0, 4.9000000e-325, "24");
-- select * from T;
-- insert into T values (34, 34, 34.0, -4.9000000e-325, "34");
-- select * from T;
-- insert into T(m_int, m_long, m_double, m_string) values (44, 44, 1.797694e+308, "44");
-- select * from T;
-- insert into T(m_int, m_long, m_double, m_string) values (54, 54, -1.797694e+308, "54");
-- select * from T;
-- insert into T(m_int, m_long, m_double, m_string) values (64, 64, 4.9000000e-325, "64");
-- select * from T;
-- insert into T(m_int, m_long, m_double, m_string) values (74, 74, -4.9000000e-325, "74");
-- select * from T;

-- string out of range --
insert into T values (5, 5, 5.0, 5.0, "123456");
select * from T;
insert into T(m_int, m_long, m_float, m_string) values (15, 15, 15.0, "123456");
select * from T;

-- drop database --
drop database test;
show databases;