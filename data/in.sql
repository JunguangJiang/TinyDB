CREATE DATABASE dbName;
show databases;
use database dbName;

CREATE TABLE myTable(a int, b STRING(39), c float not null, d double, e long, primary key(a,b));
create table person(name string(256), ID int not null, primary key(ID));
show database dbName;
show table myTable;
insert into myTable(a,b,c,d,e) values(12,'blob',2.2,4.4,3000);
delete from myTable where d=4.4;
update myTable set a=0 where b='blob';

create table newTable(a int);
select myTable.c, newTable.a, d from myTable JOIN newTable ON myTable.a = newTable.a;
select name from person where ID > 3 and ID < 10.1;

drop table myTable;
drop database dbName;
