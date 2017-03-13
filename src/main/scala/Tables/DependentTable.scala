package Tables

import Providers.DBProvider
import models.Dependent

trait DependentTable extends EmployeeTable {

  this: DBProvider =>
  import driver.api._

  class DependentTable(tag: Tag) extends Table[Dependent](tag, "dependent_table") {
    val depId = column[Int]("depId")
    val depName = column[String]("depName")
    val relation = column[String]("relation")
    val depAge = column[Option[Int]]("depAge")
    def employeeDependentFk = foreignKey(
      "employee_dependent_fk", depId, employeeTableQuery)(_.id)
    def * = (depId, depName, relation, depAge) <>(Dependent.tupled, Dependent.unapply)
  }
  val dependentTableQuery = TableQuery[DependentTable]
}