# slick-assignment
Extend the functionality of 'Employee-Project' to 'Employee-Project-Dependent' scenario to support multiple database connectivity.

A Dependent must have associated employees id, name of dependent, relation of dependent with the employee, age of the dependent which must be an optional field persisted as null in the database if not present.
(Please, first refer slick documentation for your queries).

Select any one slick supported database except PostgreSQL(already done) and H2(will be covered later while testing) and implement the extended functionality.
Include following operations on database (for Employee, Dependent and Project)
* CREATE
* INSERT
* DELETE
* UPDATE
* InsertOrUpdate (Upsert)
* GetAll(To get all the elements of a table)
* Any one valid real life operation of your choice other than the ones mentioned above(use your imagination)
