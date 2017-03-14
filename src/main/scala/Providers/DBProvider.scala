package Providers

import slick.jdbc.JdbcProfile

trait DBProvider {

  val driver: JdbcProfile
  import driver.api._
  val db: Database

}

