package controllers

import play.api._
import play.api.mvc._

import play.api.libs.ws.WS
import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits._
import java.util.concurrent.TimeoutException

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

}