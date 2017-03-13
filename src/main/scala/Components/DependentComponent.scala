package Components

import Providers.DBProvider
import Tables.DependentTable
import models.Dependent

object DependentComponent extends DependentTable {

  this: DBProvider =>
  import driver.api._

  def create = db.run(dependentTableQuery.schema.create)

  def insert(dep: Dependent) = db.run {
    dependentTableQuery += dep
  }

  def delete(id: Int) = {
    val query = dependentTableQuery.filter(_.depId === id)
    val action = query.delete
    db.run(action)
  }

  def updateName(depId: Int, depName: String) = {
    val query = dependentTableQuery.filter(_.depId === depId).map(_.depName).update(depName)
    db.run(query)
  }

  def insertOrUpdate(dependent: Dependent) = {
    val query = dependentTableQuery.insertOrUpdate(dependent)
    db.run(query)
  }

  def getAll = db.run(dependentTableQuery.result)

  def find(id: Int) = {
    val query = dependentTableQuery.filter(_.depId === id).result.headOption
    db.run(query)
  }

}


