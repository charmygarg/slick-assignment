package Components

import Providers.DBProvider
import Tables.ProjectTable
import models.Project

import scala.concurrent.Future


object ProjectComponent extends ProjectTable {

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

}


