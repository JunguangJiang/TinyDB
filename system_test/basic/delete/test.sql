CREATE DATABASE dbName;
USE DATABASE dbName;

CREATE TABLE student(
  name string(100) not null,
  ID long,
  credits int,
  GPA float,
  scores double,
  PRIMARY KEY (ID)
);
INSERT INTO student values("s1", 1, 33, 3.5, 90.0);
INSERT INTO student values("s2", 2, 100, 3.6, 94.0);
INSERT INTO student values("s3", 3, 23, 3.3, 70.0);
INSERT INTO student values("s4", 4, 50, 4.0, 98.1);
INSERT INTO student values("s5", 5, 33, 3.55, 91.4);
INSERT INTO student values("s6", 6, 35, 3.55, 92.0);
INSERT INTO student values("s7", 7, 35, 3.53, 91.0);
INSERT INTO student values('s8', 8, 36, 3.63, 94.3);
INSERT INTO student(name, ID) values("s9", 9);

-- delete and predicate --
DELETE FROM student WHERE gpa > 3.5 and scores <= 91.0;
SELECT name, id, credits, gpa, scores FROM student;

-- delete twice --
DELETE FROM student WHERE id = 8;
SELECT name, id, credits, gpa, scores FROM student;

DELETE FROM student WHERE id = 8;
SELECT name, id, credits, gpa, scores FROM student;

-- delete or predicate --
DELETE FROM student WHERE gpa = 3.55 or credits > 40;
SELECT name, id, credits, gpa, scores FROM student;

DROP TABLE student;
DROP DATABASE dbName;
