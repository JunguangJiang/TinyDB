create database test;
use database test;

create table T1 (
id long not null,
i1 int,
f1 float,
d1 double,
s1 string(100) not null,
primary key(id)
);

create table T2 (
id long,
i2 int,
f2 float,
d2 double,
s2 string(50) not null
);

create table T3 (
id long,
i3 int,
f3 float,
d3 double,
s3 string(50),
primary key (id)
);

insert into T1 values (1, 1, 1.0, 1.0, "1");
insert into T1 values (2, 2, 2.0, 2.0, "2");
insert into T1 values (3, 3, 3.0, 3.0, "3");
insert into T1 values (4, 4, 4.0, 4.0, "4");
insert into T1 values (5, 5, 5.0, 5.0, "5");
insert into T1 values (6, 6, 6.0, 6.0, "6");
insert into T1 values (7, 7, 7.0, 7.0, "7");
insert into T1 values (8, 8, 8.0, 8.0, "8");
insert into T1 values (9, 9, 9.0, 9.0, "9");
insert into T1 values (10, 10, 10.0, 10.0, "10");
insert into T1 values (11, 11, 11.0, 11.0, "11");
insert into T1 values (12, 12, 12.0, 12.0, "12");
insert into T1 values (13, 13, 13.0, 13.0, "13");
insert into T1 values (14, 14, 14.0, 14.0, "14");
insert into T1 values (15, 15, 15.0, 15.0, "15");
insert into T1 values (16, 16, 16.0, 16.0, "16");
insert into T1 values (17, 17, 17.0, 17.0, "17");
insert into T1 values (18, 18, 18.0, 18.0, "18");
insert into T1 values (19, 19, 19.0, 19.0, "19");
insert into T1 values (20, 20, 20.0, 20.0, "20");
insert into T1 values (21, null, 0.0, 0.0, "0");
insert into T1 values (22, 0, null, 0.0, "0");
insert into T1 values (23, 0, 0.0, null, "0");

insert into T2 values (1, 1, 1.0, 1.0, "1");
insert into T2 values (2, 2, 2.0, 2.0, "2");
insert into T2 values (3, 3, 3.0, 3.0, "3");
insert into T2 values (4, 4, 4.0, 4.0, "4");
insert into T2 values (5, 5, 5.0, 5.0, "5");
insert into T2 values (6, 6, 6.0, 6.0, "6");
insert into T2 values (7, 7, 7.0, 7.0, "7");
insert into T2 values (8, 8, 8.0, 8.0, "8");
insert into T2 values (9, 9, 9.0, 9.0, "9");
insert into T2 values (10, 10, 10.0, 10.0, "10");
insert into T2 values (11, 11, 11.0, 11.0, "11");
insert into T2 values (12, 12, 12.0, 12.0, "12");
insert into T2 values (13, 13, 13.0, 13.0, "13");
insert into T2 values (14, 14, 14.0, 14.0, "14");
insert into T2 values (15, 15, 15.0, 15.0, "15");
insert into T2 values (16, 16, 16.0, 16.0, "16");
insert into T2 values (17, 17, 17.0, 17.0, "17");
insert into T2 values (18, 18, 18.0, 18.0, "18");
insert into T2 values (19, 19, 19.0, 19.0, "19");
insert into T2 values (20, 20, 20.0, 20.0, "20");
insert into T2 values (20, null, 0.0, 0.0, "0");
insert into T2 values (21, 0, null, 0.0, "0");
insert into T2 values (21, 0, 0.0, null, "0");

insert into T3 values (1, 1, 1.0, 1.0, "1");
insert into T3 values (2, 2, 2.0, 2.0, "2");
insert into T3 values (3, 3, 3.0, 3.0, "3");
insert into T3 values (4, 4, 4.0, 4.0, "4");
insert into T3 values (5, 5, 5.0, 5.0, "5");

select * from T1 join T2 on T1.id = T2.id;

select * from T1 join T2 on T1.id > T2.id where T2.id >= 20;

select * from T2 join T1 on T1.id <> T2.id where T1.i1 is null and T2.i2 is null;

select * from T1 join T2 on T1.id = T2.id join T3 on T3.id = T2.id;
drop database test;
