
DROP TABLE IF EXISTS experienced_employee;

CREATE TABLE IF NOT EXISTS experienced_employee(id int PRIMARY KEY, name varchar(200), experience double);

DROP TABLE IF EXISTS project_table;

CREATE TABLE IF NOT EXISTS project_table(projId int PRIMARY KEY, projName varchar(200));