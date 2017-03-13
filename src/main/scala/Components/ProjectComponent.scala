package Components

import Providers.DBProvider
import Tables.ProjectTable
import models.Project


object ProjectComponent extends ProjectTable {

  this: DBProvider =>
  import driver.api._

  def create = db.run(projectTableQuery.schema.create)

  def insert(proj: Project) = db.run {
    projectTableQuery += proj
  }

  def delete(name: String) = {
    val query = projectTableQuery.filter(_.projName === name)
    val action = query.delete
    db.run(action)
  }

  def updateName(projId: Int, projName: String) = {
    val query = projectTableQuery.filter(_.projId === projId).map(_.projName).update(projName)
    db.run(query)
  }

  def insertOrUpdate(project: Project) ={
    val query = projectTableQuery.insertOrUpdate(project)
    db.run(query)
  }

  def getAll = db.run(projectTableQuery.result)

  def find(id: Int) = {
    val query = projectTableQuery.filter(_.projId === id).result.headOption
    db.run(query)
  }

}

