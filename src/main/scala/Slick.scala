import Components.{ProjectComponent, DependentComponent, EmployeeComponent}
import models.{Dependent, Project, Employee}
import scala.concurrent.Await
import scala.concurrent.duration._

object Slick extends App {

  EmployeeComponent.create

  EmployeeComponent.insert(Employee(1, "Charmy", 2.3D))
  EmployeeComponent.insert(Employee(2, "Simar", 1.3D))

  val allEmployees = Await.result(EmployeeComponent.getAll, 100000.second)
  println("All Employees: " + allEmployees)

  ProjectComponent.create

  ProjectComponent.insert(Project(1, "Scala"))
  ProjectComponent.insert(Project(2, "Play"))

  val allProjects = Await.result(ProjectComponent.getAll, 100000.second)
  println("All Projects: " + allProjects)

  ProjectComponent.delete("Play")
  val allProjectsAfterDel = Await.result(ProjectComponent.getAll, 100000.second)
  println("All Projects after deletion: " + allProjectsAfterDel)

  ProjectComponent.updateName(1, "Akka")
  val allProjectsAfterUpdate = Await.result(ProjectComponent.getAll, 100000.second)
  println("All Projects after updating: " + allProjectsAfterUpdate)

  ProjectComponent.insertOrUpdate(Project(3, "Spark"))
  val allProjectsAfterUpsert = Await.result(ProjectComponent.getAll, 100000.second)
  println("All Projects after upsert: " + allProjectsAfterUpsert)

  val findProject = Await.result(ProjectComponent.find(1), 100000.second)
  println("Finding Projects: " + findProject)

  val crossJoinProject = Await.result(ProjectComponent.crossJoin, 100000.second)
  println("After crossJoin Employee and Project: " + crossJoinProject)

  val innerJoinProject = Await.result(ProjectComponent.innerJoin, 100000.second)
  println("After innerJoin Employee and Project: " + innerJoinProject)

  val leftJoinProject = Await.result(ProjectComponent.leftOuterJoin, 100000.second)
  println("After leftOuterJoin Employee and Project: " + leftJoinProject)

  val fullJoinProject = Await.result(ProjectComponent.fullJoin, 100000.second)
  println("After fullJoin Employee and Project: " + fullJoinProject)

  val maxProj = Await.result(ProjectComponent.getMax, 100000.second)
  println("Getting maximum: " + maxProj)

  val minProj = Await.result(ProjectComponent.getMin, 100000.second)
  println("Getting minimum: " + minProj)

  val plainSqlProj = Await.result(ProjectComponent.plainSql, 100000.second)
  println("Plain Sql in Project: " + plainSqlProj)

  DependentComponent.create

  DependentComponent.insert(Dependent(1, "Archit", "Brother", None))
  DependentComponent.insert(Dependent(2, "Simmi", "Sister", Some(19)))

  val allDependents = Await.result(DependentComponent.getAll, 100000.second)
  println("All Dependents:" + allDependents)

  val crossJoinDependent = Await.result(DependentComponent.crossJoin, 100000.second)
  println("After crossJoin Employee and Dependent: " + crossJoinDependent)

  val innerJoinDependent = Await.result(DependentComponent.innerJoin, 100000.second)
  println("After innerJoin Employee and Dependent: " + innerJoinDependent)

  val leftJoinDependent = Await.result(DependentComponent.leftOuterJoin, 100000.second)
  println("After leftOuterJoin Employee and Dependent: " + leftJoinDependent)

  val fullJoinDependent = Await.result(DependentComponent.fullJoin, 100000.second)
  println("After fullJoin Employee and Dependent: " + fullJoinDependent)

  val max = Await.result(DependentComponent.getMax, 100000.second)
  println("Getting maximum: " + max)

  val min = Await.result(DependentComponent.getMin, 100000.second)
  println("Getting maximum: " + min)

  val plainSqlDep = Await.result(DependentComponent.plainSql, 100000.second)
  println("Plain Sql in Project: " + plainSqlDep)

  Thread.sleep(30000)

}

