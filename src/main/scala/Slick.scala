import Components.{ProjectComponent, DependentComponent, EmployeeComponent}
import models.{Dependent, Project, Employee}
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object Slick extends App {

  EmployeeComponent.create

  EmployeeComponent.insert(Employee(1, "Charmy", 2.3D))
  EmployeeComponent.insert(Employee(2, "Simar", 1.3D))

  val allEmployees = Await.result(EmployeeComponent.getAll, 100000.second)
  println("All Employees:" + allEmployees)

  ProjectComponent.create

  ProjectComponent.insert(Project(1, "Scala"))
  ProjectComponent.insert(Project(1, "Play"))

  val allProjects = Await.result(ProjectComponent.getAll, 100000.second)
  println("All Projects:" + allProjects)

  DependentComponent.create

  DependentComponent.insert(Dependent(1, "Archit", "Brother", Some(17)))
  DependentComponent.insert(Dependent(2, "Simmi", "Sister", Some(19)))

  val allDependents = Await.result(DependentComponent.getAll, 100000.second)
  println("All Dependents:" + allDependents)

  Thread.sleep(30000)

}
