package controllers

import play.api._
import play.api.mvc._

object Displays extends Controller {

  def index = Action {
    Ok(views.html.displays("Your displays: "))
  }

}
