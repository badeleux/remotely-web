package controllers

import play.api._
import play.api.mvc._

import play.api.libs.ws.WS
import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits._
import java.util.concurrent.TimeoutException
import scala.sys.process._

object Application extends Controller {

  def index = Action {

    val cmd = Seq("pgrep", "-l", "-f", "192.168.0.1")
    print (cmd lines)
    Ok(views.html.index("Your new application is ready."))
  }

}