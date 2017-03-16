import Components.EmployeeComponent
import models.Employee
import org.scalatest.AsyncFunSuite

class EmployeeSpec extends AsyncFunSuite {

  object emp extends EmployeeComponent with H2Provider

  test("it should Add new Employee ") {
    emp.insert(Employee(3, "Himanshu" ,0.5)).map(x => assert(x == 1))
  }

  test("it should Update Employee record ") {
    emp.updateName(1, "Shivangi").map(x => assert(x == 1))
  }

  test("it should Delete Employee ") {
    emp.delete(0.0).map(x => assert(x == 1))
  }

  test("it should Insert or update Employee") {
    emp.insertOrUpdate(Employee(2, "Simar", 1)).map(x => assert(x == 1))
  }

  test("it should Insert if not present otherwise update Employee") {
    emp.insertOrUpdate(Employee(2, "Simar", 0.5)).map(x => assert(x == 1))
  }

  test("it should Get list of all Employees") {
    emp.getAll.map(x => assert(x == List(Employee(1, "charmy", 0.0))))
  }

  test("it should Find employee") {
    emp.find(1).map(x => assert(x contains Employee(1, "charmy", 0.0)))
  }

  test("it should create DBIO action in Employee") {
    emp.dbioAction(Employee(4, "anurag", 1.0),Employee(5, "vaibhav", 3.0)).map(x => assert(x == (1,1)))
  }

}
