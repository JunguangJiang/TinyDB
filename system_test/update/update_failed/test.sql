create database test;
use database test;
create table T(
m_int int not null,
m_long long,
m_float float,
m_double double,
m_string string(10) not null,
PRIMARY KEY (m_long)
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

update T set m_long=21.0 where m_long = 20;
update T set m_long = "444" where m_long = 19;

update T set m_int = 233.9 where m_long = 20;
update T set m_int = "444" where m_long = 19;

update T set m_float = "RRR" where m_long = 19;

update T set m_double = "HHH" where m_long = 19;

update T set m_string = 44 where m_long = 20;
update T set m_string = 45.5 where m_long = 19;

-- m_string should not be null
update T set m_string = null where m_long = 18;

-- primary key conflict with origin
update T set m_long=19 where m_long=18;

-- primary key conflicts
update T set m_long = 23;
select * from T;

drop database test;