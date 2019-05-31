create database test;
use database test;

create table T (
m_int int not null,
m_long long,
m_float float,
m_double double,
m_string string(5) not null
);
insert into T values (1, 1, 1.0, 1.0, "1");
insert into T values (2, 2, 2.0, 2.0, "2");
insert into T values (3, 3, 3.0, 3.0, "3");
insert into T values (4, 4, 4.0, 4.0, "4");
insert into T values (5, 5, 5.0, 5.0, "5");
insert into T values (6, 6, 6.0, 6.0, "6");
insert into T values (7, 7, 7.0, 7.0, "7");
insert into T values (8, 8, 8.0, 8.0, "8");
insert into T values (9, 9, 9.0, 9.0, "9");
insert into T values (10, 10, 10.0, 10.0, "10");
insert into T values (11, 11, 11.0, 11.0, "11");
insert into T values (12, 12, 12.0, 12.0, "12");
insert into T values (13, 13, 13.0, 13.0, "13");
insert into T values (14, 14, 14.0, 14.0, "14");
insert into T values (15, 15, 15.0, 15.0, "15");
insert into T values (16, 16, 16.0, 16.0, "16");
insert into T values (17, 17, 17.0, 17.0, "17");
insert into T values (18, 18, 18.0, 18.0, "18");
insert into T values (19, 19, 19.0, 19.0, "19");
insert into T values (20, 20, 20.0, 20.0, "20");
show databases;
select * from T;
-- a database exist --

-- test certain comparison operator --
delete from T where m_int = 10;
-- except 10 --
select * from T;
delete from T where m_int < 2;
-- [2,9],[11, 20] --
select * from T;
delete from T where m_int > 19;
-- [2,9],[11, 19] --
select * from T;
delete from T where m_int <= 4;
-- [5,9],[11, 19] --
select * from T;
delete from T where m_int >= 10;
-- [5, 9] --
select * from T;
delete from T where m_int <> 7;
-- { 7 } --
select * from T;
-- NOTHING TO DELETE --
delete from T where m_int != 10;
-- { 7 } --
select * from T;

-- set null --
delete from T where m_int != 100;
select * from T;

-- insert values --
insert into T values (1, 1, 1.0, 1.0, "1");
insert into T values (2, 2, 2.0, 2.0, "2");
insert into T values (3, 3, 3.0, 3.0, "3");
insert into T values (4, 4, 4.0, 4.0, "4");
insert into T values (5, 5, 5.0, 5.0, "5");
insert into T values (6, 6, 6.0, 6.0, "6");
insert into T values (7, 7, 7.0, 7.0, "7");
insert into T values (8, 8, 8.0, 8.0, "8");
insert into T values (9, 9, 9.0, 9.0, "9");
insert into T values (10, 10, 10.0, 10.0, "10");
insert into T values (11, 11, 11.0, 11.0, "11");
insert into T values (12, 12, 12.0, 12.0, "12");
insert into T values (13, 13, 13.0, 13.0, "13");
insert into T values (14, 14, 14.0, 14.0, "14");
insert into T values (15, 15, 15.0, 15.0, "15");
insert into T values (16, 16, 16.0, 16.0, "16");
insert into T values (17, 17, 17.0, 17.0, "17");
insert into T values (18, 18, 18.0, 18.0, "18");
insert into T values (19, 19, 19.0, 19.0, "19");
insert into T values (20, 20, 20.0, 20.0, "20");


delete from T where m_long = 10;
-- except 10 --
select * from T;
delete from T where m_long < 2;
-- [2,9],[11, 20] --
select * from T;
delete from T where m_long > 19;
-- [2,9],[11, 19] --
select * from T;
delete from T where m_long <= 4;
-- [5, 9],[11, 19] --
select * from T;
delete from T where m_long >= 10;
-- [5, 9] --
select * from T;
delete from T where m_long <> 7;
-- { 7 } --
select * from T;
-- NOTHING TO DELETE --
delete from T where m_long != 10;
-- { 7 } --
select * from T;

-- set null --
delete from T where m_long != 100;
select * from T;

-- insert values --
insert into T values (1, 1, 1.0, 1.0, "1");
insert into T values (2, 2, 2.0, 2.0, "2");
insert into T values (3, 3, 3.0, 3.0, "3");
insert into T values (4, 4, 4.0, 4.0, "4");
insert into T values (5, 5, 5.0, 5.0, "5");
insert into T values (6, 6, 6.0, 6.0, "6");
insert into T values (7, 7, 7.0, 7.0, "7");
insert into T values (8, 8, 8.0, 8.0, "8");
insert into T values (9, 9, 9.0, 9.0, "9");
insert into T values (10, 10, 10.0, 10.0, "10");
insert into T values (11, 11, 11.0, 11.0, "11");
insert into T values (12, 12, 12.0, 12.0, "12");
insert into T values (13, 13, 13.0, 13.0, "13");
insert into T values (14, 14, 14.0, 14.0, "14");
insert into T values (15, 15, 15.0, 15.0, "15");
insert into T values (16, 16, 16.0, 16.0, "16");
insert into T values (17, 17, 17.0, 17.0, "17");
insert into T values (18, 18, 18.0, 18.0, "18");
insert into T values (19, 19, 19.0, 19.0, "19");
insert into T values (20, 20, 20.0, 20.0, "20");

delete from T where m_double = 10.0;
-- except 10 --
select * from T;
delete from T where m_double < 2.0;
-- [2, 9], [11, 20] --
select * from T;
delete from T where m_double > 19.0;
-- [2, 9], [11, 19] --
select * from T;
delete from T where m_double <= 4.0;
-- [5, 9], [11, 19] --
select * from T;
delete from T where m_double >= 10.0;
-- [5, 9] --
select * from T;
delete from T where m_double <> 7.0;
-- { 7 } --
select * from T;
-- NOTHING TO DELETE --
delete from T where m_double != 10.0;
-- { 7 } --
select * from T;

-- set null --
delete from T where m_double != 100.0;
select * from T;

-- insert values --
insert into T values (1, 1, 1.0, 1.0, "1");
insert into T values (2, 2, 2.0, 2.0, "2");
insert into T values (3, 3, 3.0, 3.0, "3");
insert into T values (4, 4, 4.0, 4.0, "4");
insert into T values (5, 5, 5.0, 5.0, "5");
insert into T values (6, 6, 6.0, 6.0, "6");
insert into T values (7, 7, 7.0, 7.0, "7");
insert into T values (8, 8, 8.0, 8.0, "8");
insert into T values (9, 9, 9.0, 9.0, "9");
insert into T values (10, 10, 10.0, 10.0, "10");
insert into T values (11, 11, 11.0, 11.0, "11");
insert into T values (12, 12, 12.0, 12.0, "12");
insert into T values (13, 13, 13.0, 13.0, "13");
insert into T values (14, 14, 14.0, 14.0, "14");
insert into T values (15, 15, 15.0, 15.0, "15");
insert into T values (16, 16, 16.0, 16.0, "16");
insert into T values (17, 17, 17.0, 17.0, "17");
insert into T values (18, 18, 18.0, 18.0, "18");
insert into T values (19, 19, 19.0, 19.0, "19");
insert into T values (20, 20, 20.0, 20.0, "20");

delete from T where m_float = 10.0;
-- except 10 --
select * from T;
delete from T where m_float < 2.0;
-- [2, 9], [11, 20] --
select * from T;
delete from T where m_float > 19.0;
-- [2, 9], [11, 19] --
select * from T;
delete from T where m_float <= 4.0;
-- [5, 9],[11, 19] --
select * from T;
delete from T where m_float >= 10.0;
-- [5, 9] --
select * from T;
delete from T where m_float <> 7.0;
-- { 7 } --
select * from T;
-- NOTHING TO DELETE --
delete from T where m_float != 10.0;
-- { 7 } --
select * from T;

-- set null --
delete from T where m_float != 100.0;
select * from T;

-- insert values --
insert into T values (1, 1, 1.0, 1.0, "1");
insert into T values (2, 2, 2.0, 2.0, "2");
insert into T values (3, 3, 3.0, 3.0, "3");
insert into T values (4, 4, 4.0, 4.0, "4");
insert into T values (5, 5, 5.0, 5.0, "5");
insert into T values (6, 6, 6.0, 6.0, "6");
insert into T values (7, 7, 7.0, 7.0, "7");
insert into T values (8, 8, 8.0, 8.0, "8");
insert into T values (9, 9, 9.0, 9.0, "9");
insert into T values (10, 10, 10.0, 10.0, "10");
insert into T values (11, 11, 11.0, 11.0, "11");
insert into T values (12, 12, 12.0, 12.0, "12");
insert into T values (13, 13, 13.0, 13.0, "13");
insert into T values (14, 14, 14.0, 14.0, "14");
insert into T values (15, 15, 15.0, 15.0, "15");
insert into T values (16, 16, 16.0, 16.0, "16");
insert into T values (17, 17, 17.0, 17.0, "17");
insert into T values (18, 18, 18.0, 18.0, "18");
insert into T values (19, 19, 19.0, 19.0, "19");
insert into T values (20, 20, 20.0, 20.0, "20");


delete from T where m_string = "10";
-- except 10 --
select * from T;

delete from T where m_string != "6";
-- only 6 --
select * from T;
delete from T where m_string <> "6";
-- only 6 --
select * from T;
-- set null --
delete from T where m_int != 100;
select * from T;


delete from T where m_string < "10";
select * from T;
delete from T where m_string > "10";
select * from T;
delete from T where m_string <= "10";
select * from T;
delete from T where m_string >= "10";
select * from T;

-- drop database --
drop database test;
show databases;