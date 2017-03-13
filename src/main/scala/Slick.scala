import Components.{ProjectComponent, DependentComponent, EmployeeComponent}
import models.{Dependent, Project, Employee}
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object Slick extends App {

  EmployeeComponent.create

  EmployeeComponent.insert(Employee(1, "Charmy", 2.3D))
  EmployeeComponent.insert(Employee(2, "Simar", 1.3D))

  ProjectComponent.create

  ProjectComponent.insert(Project(1, "Scala"))
  ProjectComponent.insert(Project(1, "Play"))

  DependentComponent.create

  DependentComponent.insert(Dependent(1, "Saksham", "Brother", Some(17)))
  DependentComponent.insert(Dependent(2, "Ashia", "Sister", Some(19)))

  val allEmployees = Await.result(EmployeeComponent.getAll, 10000.second)

  println("All Employees:"+allEmployees)
  Thread.sleep(30000)

}
