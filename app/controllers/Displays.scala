package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.Display


object Displays extends Controller {
  
  val displayForm = Form(
  mapping(
    "name" -> nonEmptyText,
    "ip_address" -> nonEmptyText
  )
  ((name, ip_address) =>
    Display(1L, name, ip_address)
  )
  ((display: Display) =>
    Option((display.name, display.ip_screen_address))
  )
  )

  val runXAppForm = Form(
    single(
      "xApp" -> nonEmptyText
    )
  )


  def index = Action {
    Ok(views.html.displays(Display.all(), displayForm, runXAppForm))
  }

  def newDisplay = Action { implicit request =>
    displayForm.bindFromRequest.fold(
      errors => BadRequest(views.html.displays(Display.all(), errors, runXAppForm)),
      display => {
        Display.create(display.name, display.ip_screen_address)
        Redirect(routes.Displays.index)
      }
    )
    
  }

  def deleteDisplay(id: Long) = Action { 
    Display.delete(id)
    Redirect(routes.Displays.index)
  }

  def runXApp(id: Long) = Action { implicit request =>
    runXAppForm.bindFromRequest.fold(
      errors => BadRequest(views.html.displays(Display.all(), displayForm, errors)),
      xAppName => {

        Redirect(routes.Displays.index)
      }

    )


  }

}
