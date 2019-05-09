CREATE DATABASE dbName;
USE DATABASE dbName;

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

CREATE TABLE teacher(
  name string(100) not null,
  ID long,
  title string(100),
  school_name string(20),
  PRIMARY KEY(ID)
);

INSERT INTO student values("s1", 1, 200, 3.1, 90.0, "THU");
INSERT INTO student values("s2", 2, 181, 3.6, 94.0, "THU");
INSERT INTO student values("s3", 3, 199, 2.2, 70.0, "THU");
INSERT INTO student values("s4", 4, 120, 3.8, 98.1, "PKU");
INSERT INTO student values("s5", 5, 123, 3.75, 91.4, "PKU");
INSERT INTO student values("s6", 6, 170, 3.55, 92.0, "ZJU");
INSERT INTO student values("s7", 7, 166, 3.9, 91.0, "ZJU");
INSERT INTO student values('s8', 8, 150, 4.0, 94.3, "ZJU");
INSERT INTO student(name, ID) values("s9", 9);

INSERT INTO school VALUES ("THU", 1, 1);
INSERT INTO school VALUES ("PKU", 2, 2);
INSERT INTO school(ID, name, rank) VALUES (3, "ZJU", 3);

INSERT INTO teacher VALUES ("t1", 1, "professor", "THU");
INSERT INTO teacher VALUES ("t2", 2, "associate professor", "THU");
INSERT INTO teacher VALUES ("t3", 3, "professor", "PKU");
INSERT INTO teacher VALUES ("t4", 4, "lecturer", "ZJU");


SELECT student.name, credits, gpa, school.name FROM student JOIN school ON student.school_name = school.name;

SELECT student.name, credits, gpa, school.name FROM student JOIN school ON student.school_name = school.name WHERE student.GPA >= 3.6;

SELECT student.name, credits, gpa, school.name FROM student JOIN school ON student.school_name >= school.name and school.name != "ZJU";

SELECT teacher.name, student.name, school.name FROM student JOIN school ON student.school_name = school.name
  JOIN teacher ON teacher.school_name = school.name WHERE student.scores > 90.0 and teacher.title = 'professor';

DROP TABLE student;
DROP TABLE school;
DROP TABLE teacher;
DROP DATABASE dbName;
