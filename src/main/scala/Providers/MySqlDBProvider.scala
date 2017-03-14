package Providers

import slick.jdbc.MySQLProfile

trait MySqlDBProvider extends DBProvider{

  val driver = MySQLProfile
  import driver.api._
  val db = Database.forConfig("myMySqlDB")

}

