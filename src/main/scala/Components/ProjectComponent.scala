package Components

import Providers.{MySqlDBProvider, DBProvider}
import Tables.ProjectTable
import models.Project

import scala.concurrent.Future


trait ProjectComponent extends ProjectTable {

  this: DBProvider =>
  import driver.api._

  def create: Future[Unit] = db.run(projectTableQuery.schema.create)

  def insert(proj: Project): Future[Int] = db.run {
    projectTableQuery += proj
  }

  def delete(name: String): Future[Int] = {
    val query = projectTableQuery.filter(_.projName === name)
    val action = query.delete
    db.run(action)
  }

  def updateName(projId: Int, projName: String): Future[Int] = {
    val query = projectTableQuery.filter(_.projId === projId).map(_.projName).update(projName)
    db.run(query)
  }

  def insertOrUpdate(project: Project): Future[Int] ={
    val query = projectTableQuery.insertOrUpdate(project)
    db.run(query)
  }

  def getAll: Future[List[Project]] = db.run(projectTableQuery.to[List].result)

  def find(id: Int): Future[Option[Project]] = {
    val query = projectTableQuery.filter(_.projId === id).result.headOption
    db.run(query)
  }

  def crossJoin: Future[List[(String, String)]] = {
    val query = for {
      (e, p) <- employeeTableQuery join projectTableQuery
    } yield (e.name, p.projName)
    db.run(query.to[List].result)
  }

  def innerJoin: Future[List[(String, String)]] = {
    val query = for {
      (e, p) <- employeeTableQuery join projectTableQuery on(_.id === _.projId)
    } yield (e.name, p.projName)
    db.run(query.to[List].result)
  }

  def leftOuterJoin: Future[List[(String, Option[Int])]] = {
    val query = for {
      (e, p) <- employeeTableQuery joinLeft projectTableQuery on(_.id === _.projId)
    } yield (e.name, p.flatMap(_.projId))
    db.run(query.to[List].result)
  }

  def fullJoin: Future[List[(Option[String], Option[Int])]] = {
    val query = for {
      (e, p) <- employeeTableQuery joinFull projectTableQuery on (_.id === _.projId)
    } yield (e.flatMap(_.name), p.flatMap(_.projId))
    db.run(query.to[List].result)
  }

  def getMax: Future[Option[Int]] = {
    val query = projectTableQuery.map(_.projId).max
    db.run(query.result)
  }

  def getMin: Future[Option[Int]] = {
    val query = projectTableQuery.map(_.projId).min
    db.run(query.result)
  }

  def plainSql: Future[Vector[(Int, String)]] = {
    val action = sql"select projId, projName from project_table".as[(Int,String)]
    db.run(action)
  }

  def dbioAction(proj: Project, proj1: Project) = {
    val ins1: DBIO[Int] = projectTableQuery += proj
    val ins2: DBIO[Int] = projectTableQuery += proj
    val action: DBIO[(Int, Int)] = ins1 zip ins2
    db.run(action)
  }

}

object ProjectComponent extends ProjectComponent with MySqlDBProvider



