package Components

import Providers.{MySqlDBProvider, DBProvider}
import Tables.EmployeeTable
import models.Employee
import scala.concurrent.Future

trait EmployeeComponent extends EmployeeTable {

  this: DBProvider =>
  import driver.api._

  def create: Future[Unit] = db.run(employeeTableQuery.schema.create)

  def insert(emp: Employee): Future[Int] = db.run {
    employeeTableQuery += emp
  }

  def delete(exp: Double): Future[Int] = {
    val query = employeeTableQuery.filter(_.experience === exp)
    val action = query.delete
    db.run(action)
  }

  def updateName(id: Int, name: String): Future[Int] = {
    val query = employeeTableQuery.filter(_.id === id).map(_.name).update(name)
    db.run(query)
  }

  def insertOrUpdate(employee: Employee): Future[Int] ={
    val query = employeeTableQuery.insertOrUpdate(employee)
    db.run(query)
  }

  def getAll: Future[List[Employee]] = db.run(employeeTableQuery.to[List].result)

  def find(id: Int): Future[Option[Employee]] = {
    val query = employeeTableQuery.filter(_.id === id).result.headOption
    db.run(query)
  }

  def dbioAction(emp: Employee, emp1: Employee) = {
    val ins1: DBIO[Int] = employeeTableQuery += emp
    val ins2: DBIO[Int] = employeeTableQuery += emp1
    val action: DBIO[(Int, Int)] = ins1 zip ins2
    db.run(action)
  }

}

object EmployeeComponent extends EmployeeComponent with MySqlDBProvider


