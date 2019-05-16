show databases;
-- use an non-existent database --
use database test;
-- use an existent database --
create database test1;
create database test2;
create database test3;
show databases;
use database test2;
-- use another database --
use database test1;
-- drop a database and use it --
drop database test2;
show databases;
use database test2;

-- drop the databases we create --
drop database test1;
drop database test3;




