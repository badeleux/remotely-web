package controllers

import play.api._
import play.api.mvc._

import play.api.libs.ws.WS
import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits._
import java.util.concurrent.TimeoutException
import scala.sys.process._

object Application extends Controller with Secured {

  def index = withAuth { username => implicit request =>
    Ok(views.html.index("Your new application is ready."))
  }

}
