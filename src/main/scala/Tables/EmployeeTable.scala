package Tables

import Providers.{MySqlDBProvider, DBProvider}
import models.Employee

trait EmployeeTable extends MySqlDBProvider {

  this: DBProvider =>
  import driver.api._

  class EmployeeTable(tag: Tag) extends Table[Employee](tag, "experienced_employee") {
    val id = column[Int]("id", O.PrimaryKey)
    val name = column[String]("name")
    val experience = column[Double]("experience")

    def * = (id, name, experience) <> (Employee.tupled, Employee.unapply)
  }
  val employeeTableQuery = TableQuery[EmployeeTable]

}
