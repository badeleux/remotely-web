package controllers

import play.api._
import play.api.mvc._

/**
 * Created with IntelliJ IDEA.
 * User: kamilbadyla
 * Date: 25.11.2013
 * Time: 19:15
 * To change this template use File | Settings | File Templates.
 */
object Tools extends Controller {

  def index = Action {
    Ok(views.html.tools("Your new application is ready."))
  }

}
