CREATE DATABASE dbName;
use database dbName;
create table person(name string(256) not null, ID int, weight long, height float, ratio double, primary key(ID));
insert into person(name, ID) values ("hie", 4);
insert into person(name, ID, weight, height) values ("woo", 5, 100, 165.0);
insert into person(name, ID, ratio) values ("jiang", 6, 5.4);
insert into person(name, ID) values ("jun", 7);
select person.name, ID, weight, height, ratio from person where id > 4 and ratio = 0.0;

create table man(name string(256) not null, ID int, wife string(100));
insert into man(name, ID, wife) values ("hie", 4, "woo");
insert into man values ("jiang", 6, "jun");
select man.name, man.ID, wife, weight, height from man join person on man.name = person.name where man.id = person.id and man.id=4;


delete from person where name = "jun";
update person set names = "hi" where name = "hie";
update person set ratio = 1.0 where ratio = 0.0;
select name, ID, weight, height, ratio from persons;
select names, ID, weight, height, ratio from person;
select name, ID, weight, height, ratio from person where ids=4;
select name, man.ID, wife, weight, height from man join person on man.name = person.name where man.id = person.id and man.id=4;

select man.name, man.ID, wife, weight, height from man, person where man.id = person.id and man.id=4;


insert into person(name, ID, weight) values ("hi", 4);
insert into person(name, ID, weights) values ("hi", 4, 5);

show databases;
show database dbName;
show table man;
show table woman;

drop table person;
drop table man;