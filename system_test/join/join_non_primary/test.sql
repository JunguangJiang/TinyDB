create database test;
use database test;

create table T1 (
id long,
s1 string(100) not null,
primary key(id)
);

create table T2 (
id long,
s2 string(50) not null,
primary key (id)
);

insert into T1 values (1, "Hello");
insert into T1 values (2, "World!");
insert into T1 values (3, "This");
insert into T1 values (4, "is");
insert into T1 values (5, "TinyDB.");
insert into T1 values (6, "Welcome!");

insert into T2 values (11, "Hello");
insert into T2 values (12, "World!");
insert into T2 values (13, "This");
insert into T2 values (14, "is");
insert into T2 values (15, "TinyDB.");
insert into T2 values (16, "Welcome!");

select * from T1 join T2 on T1.s1 = T2.s2;
select * from T1 join T2 on T1.s1 <> T2.s2 where T1.id > 4;
select * from T1 join T2 on T1.s1 < T2.s2 where T2.s2 <= "This";

drop database test;
