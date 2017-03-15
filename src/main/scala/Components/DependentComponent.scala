package Components

import Providers.{MySqlDBProvider, DBProvider}
import Tables.DependentTable
import models.Dependent

import scala.concurrent.Future

trait DependentComponent extends DependentTable {

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

  def crossJoin: Future[List[(String, String)]] = {
    val query = for {
      (e, d) <- employeeTableQuery join dependentTableQuery
    } yield (e.name, d.depName)
    db.run(query.to[List].result)
  }

  def innerJoin: Future[List[(String, String)]] = {
    val query = for {
      (e, d) <- employeeTableQuery join dependentTableQuery on(_.id === _.depId)
    } yield (e.name, d.depName)
    db.run(query.to[List].result)
  }

  def leftOuterJoin: Future[List[(String, Option[Int])]] = {
    val query = for {
      (e, d) <- employeeTableQuery joinLeft dependentTableQuery on(_.id === _.depId)
    } yield (e.name, d.flatMap(_.depAge))
    db.run(query.to[List].result)
  }

  def fullJoin = {
    val query = for {
      (e, d) <- employeeTableQuery joinFull dependentTableQuery on (_.id === _.depId)
    } yield (e.flatMap(_.name), d.flatMap(_.depAge))
    db.run(query.to[List].result)
  }

  def getMax = {
    val query = dependentTableQuery.map(_.depId).max
    db.run(query.result)
  }

  def getMin = {
    val query = dependentTableQuery.map(_.depId).min
    db.run(query.result)
  }

}

object DependentComponent extends DependentComponent with MySqlDBProvider




