package Tables

import Providers.DBProvider
import models.Project

trait ProjectTable extends EmployeeTable {

  this: DBProvider =>
  import driver.api._

  class ProjectTable(tag: Tag) extends Table[Project](tag, "project_table") {
    val projId = column[Int]("projId", O.PrimaryKey)
    val projName = column[String]("projName")
    def employeeProjectFk = foreignKey(
      "employee_project_fk", projId, employeeTableQuery)(_.id)
    def * = (projId, projName) <>(Project.tupled, Project.unapply)
  }
  val projectTableQuery = TableQuery[ProjectTable]
}