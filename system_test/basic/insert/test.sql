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
INSERT INTO student values('s8', 8, 35, 3.63, 94.3);
INSERT INTO student(name, ID) values("s9", 9);

-- not null test --
INSERT INTO student(name) values("s10");
INSERT INTO student(ID, credits) VALUES("s11", 33);

-- unique test
INSERT INTO student(ID, name) VALUES(8, "s8");
SELECT name, id, credits, gpa, scores from student;

-- type mismatch test
INSERT INTO student values('s10', 19, 35.5, 3.63, 94.3);

DROP TABLE student;
DROP DATABASE dbName;
