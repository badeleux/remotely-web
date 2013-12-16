package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current
import play.api.libs.concurrent.Execution.Implicits._
import scala.sys.process._

case class Display(id: Long, name: String, ip_screen_address: String)
{

  def currentlyRunningApplication() : String = {
    val cmd = Seq("pgrep", "-l", "-f", ip_screen_address)
    cmd.lines.head.split(" ")(1)
  }

}


object Display {

val display = {
  get[Long]("id") ~
  get[String]("name") ~
  get[String]("ip_screen_address") map {
    case id~name~ip_screen_address => Display(id, name, ip_screen_address)
  }
}
  def all(): List[Display] =
  {
      DB.withConnection { implicit c =>
        SQL("select * from display").as(display *)
       }
  }
  def create(name: String, ip_screen_address: String) {
    DB.withConnection { implicit c =>
      SQL("insert into display (name, ip_screen_address) values ({name}, {ip_screen_address})").on("name" -> name, "ip_screen_address" ->
      ip_screen_address).executeUpdate()
    }
  }
  def delete(id: Long) {
    DB.withConnection { implicit c =>
      SQL("delete fron display where id = {id}").on('id -> id).executeUpdate()
    
    }
  }
 



  def runXApp(name: String) {

  }
}
