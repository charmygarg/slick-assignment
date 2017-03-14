package Components

import Providers.DBProvider
import Tables.DependentTable
import models.Dependent

import scala.concurrent.Future

object DependentComponent extends DependentTable {

  this: DBProvider =>
  import driver.api._

  def create: Future[Unit] = db.run(dependentTableQuery.schema.create)

  def insert(dep: Dependent): Future[Int] = db.run {
    dependentTableQuery += dep
  }

  def delete(id: Int): Future[Int] = {
    val query = dependentTableQuery.filter(_.depId === id)
    val action = query.delete
    db.run(action)
  }

  def updateName(depId: Int, depName: String): Future[Int] = {
    val query = dependentTableQuery.filter(_.depId === depId).map(_.depName).update(depName)
    db.run(query)
  }

  def insertOrUpdate(dependent: Dependent): Future[Int] = {
    val query = dependentTableQuery.insertOrUpdate(dependent)
    db.run(query)
  }

  def getAll: Future[List[Dependent]] = db.run(dependentTableQuery.to[List].result)

  def find(id: Int): Future[Option[Dependent]] = {
    val query = dependentTableQuery.filter(_.depId === id).result.headOption
    db.run(query)
  }

}


