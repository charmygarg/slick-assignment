import Components.EmployeeComponent
import models.Employee
import org.scalatest.AsyncFunSuite

class EmployeeSpec extends AsyncFunSuite {

  object emp extends EmployeeComponent with H2Provider

  test("Add new Employee ") {
    emp.insert(Employee(3, "Himanshu" ,0.5)).map(x => assert(x == 1))
  }

  test("Update Employee record ") {
    emp.updateName(1, "Shivangi").map(x => assert(x == 1))
  }

  test("Delete Employee ") {
    emp.delete(0.0).map(x => assert(x == 1))
  }

  test("Insert or update Employee") {
    emp.insertOrUpdate(Employee(2, "Simar", 1)).map(x => assert(x == 1))
  }

  test("Insert if not present otherwise update Employee") {
    emp.insertOrUpdate(Employee(2, "Simar", 0.5)).map(x => assert(x == 1))
  }

  test("Get list of all Employees") {
    emp.getAll.map(x => assert(x == List(Employee(1, "charmy", 0.0))))
  }

  test("Find employee") {
    emp.find(1).map(x => assert(x contains Employee(1, "charmy", 0.0)))
  }

}
