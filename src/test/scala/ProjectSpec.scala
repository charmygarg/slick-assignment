import Components.ProjectComponent
import models.Project
import org.scalatest.AsyncFunSuite

/**
  * Created by knodus on 15/3/17.
  */
class ProjectSpec extends AsyncFunSuite {

  object project extends ProjectComponent with H2Provider

  test("Add new Project ") {
    project.insert(Project(3, "spark")).map(x => assert(x == 1))
  }

  test("Update Project record ") {
    project.updateName(1, "play").map(x => assert(x == 1))
  }

  test("Delete Project ") {
    project.delete("scala").map(x => assert(x == 1))
  }

  test("Insert or update Project") {
    project.insertOrUpdate(Project(2, "Akka")).map(x => assert(x == 1))
  }

  test("Insert if not present otherwise update Project") {
    project.insertOrUpdate(Project(1, "play")).map(x => assert(x == 1))
  }

  test("Get list of all Projects") {
    project.getAll.map(x => assert(x == List(Project(1, "scala"))))
  }

  test("Find Project") {
    project.find(1).map(x => assert(x contains Project(1, "scala")))
  }

  test("Cross Join Project and Employee") {
    project.crossJoin.map(x => assert(x == List(("charmy", "scala"))))
  }

  test("Inner Join Project and Employee") {
    project.innerJoin.map(x => assert(x == List(("charmy", "scala"))))
  }

  test("Left Outer Join Project and Employee") {
    project.leftOuterJoin.map(x => assert(x == List(("charmy", Some(1)))))
  }

  test("Full Join Project and Employee") {
    project.leftOuterJoin.map(x => assert(x == List(("charmy", Some(1)))))
  }

  test("Get Maximum") {
    project.getMax.map(x => assert(x contains 1))
  }

  test("Get Minimum") {
    project.getMin.map(x => assert(x contains 1))
  }

}
