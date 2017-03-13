package Providers

import slick.jdbc.PostgresProfile

trait PostgresDBProvider extends DBProvider{

  val driver = PostgresProfile
  import driver.api._
  val db = Database.forConfig("myPostgresDB")

}
