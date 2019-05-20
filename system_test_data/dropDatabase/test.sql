show databases;

create database test1;
create database test2;
create database test3;
show databases;

drop database test1;
show databases;

-- drop a non-existent database --
drop database test1;
show databases;

-- drop a used database --
use database test2;
drop database test2;
show databases;

-- drop the databases we create --
drop database test3;