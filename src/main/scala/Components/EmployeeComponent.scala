package Components

import Providers.DBProvider
import Tables.EmployeeTable
import models.Employee

object EmployeeComponent extends EmployeeTable {

  this: DBProvider =>
  import driver.api._

  def create = db.run(employeeTableQuery.schema.create)

  def insert(emp: Employee) = db.run {
    employeeTableQuery += emp
  }

  def delete(exp: Double) = {
    val query = employeeTableQuery.filter(_.experience > exp)
    val action = query.delete
    db.run(action)
  }

  def updateName(id: Int, name: String) = {
    val query = employeeTableQuery.filter(_.id === id).map(_.name).update(name)
    db.run(query)
  }

  def insertOrUpdate(employee: Employee) ={
    val query = employeeTableQuery.insertOrUpdate(employee)
    db.run(query)
  }

  def getAll = db.run(employeeTableQuery.result)

  def find(id: Int) = {
    val query = employeeTableQuery.filter(_.id === id).result.headOption
    db.run(query)
  }

}

