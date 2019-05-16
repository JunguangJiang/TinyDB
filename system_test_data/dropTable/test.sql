show databases;

create database test1;
create database test2;
show databases;
show database test1;
show database test2;

-- using database is required before drop table --
drop table T1;

use database test2;
CREATE TABLE student(
  name string(100) not null,
  ID long,
  credits int,
  GPA float,
  scores double,
  school_name string(20),
  PRIMARY KEY (ID)
);
CREATE TABLE school(
  name string(20) not null,
  ID long,
  rank int,
  PRIMARY KEY (ID)
);

use database test1;
create table T1(A int);
show databases;
show database test1;
show database test2;

use database test2;
drop table student;
show databases;
show database test1;
show database test2;

-- can't drop a non-existent database --
drop table student;
show databases;
show database test1;
show database test2;

drop table school;
show databases;
show database test1;
show database test2;

-- drop the databases we create --
drop database test1;
drop database test2;

