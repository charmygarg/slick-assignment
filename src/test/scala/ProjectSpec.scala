import Components.ProjectComponent
import models.Project
import org.scalatest.AsyncFunSuite

class ProjectSpec extends AsyncFunSuite {

  object project extends ProjectComponent with H2Provider

  test("it should add new Project ") {
    project.insert(Project(3, "spark")).map(x => assert(x == 1))
  }

  test("it should Update Project record ") {
    project.updateName(1, "play").map(x => assert(x == 1))
  }

  test("it should Delete Project ") {
    project.delete("scala").map(x => assert(x == 1))
  }

  test("it should Insert or update Project") {
    project.insertOrUpdate(Project(2, "Akka")).map(x => assert(x == 1))
  }

  test("it should Insert if not present otherwise update Project") {
    project.insertOrUpdate(Project(1, "play")).map(x => assert(x == 1))
  }

  test("it should Get list of all Projects") {
    project.getAll.map(x => assert(x == List(Project(1, "scala"))))
  }

  test("it should Find Project") {
    project.find(1).map(x => assert(x contains Project(1, "scala")))
  }

  test("it should Cross Join Project and Employee") {
    project.crossJoin.map(x => assert(x == List(("charmy", "scala"))))
  }

  test("it should Inner Join Project and Employee") {
    project.innerJoin.map(x => assert(x == List(("charmy", "scala"))))
  }

  test("it should Left Outer Join Project and Employee") {
    project.leftOuterJoin.map(x => assert(x == List(("charmy", Some(1)))))
  }

  test("it should Full Join Project and Employee") {
    project.leftOuterJoin.map(x => assert(x == List(("charmy", Some(1)))))
  }

  test("it should Get Maximum") {
    project.getMax.map(x => assert(x contains 1))
  }

  test("it should Get Minimum") {
    project.getMin.map(x => assert(x contains 1))
  }

  test("it should give all fields in PlainSQL") {
    project.plainSql.map(x => assert(x == Vector((1, "scala"))))
  }

}
