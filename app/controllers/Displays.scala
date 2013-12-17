package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.Display


object Displays extends Controller with Secured {
  
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
  
  def xAppForms(displays: List[Display]) : List[Form[String]] = 
  {
     displays.map(d => {
     Form(
        single(
         "xApp" -> nonEmptyText
        )
      ).fill(d.currentlyRunningApplication())
    })
  }


  def index = withAuth { username => implicit request =>
       Ok(views.html.displays(Display.all(), displayForm, xAppForms(Display.all())))
  }

  def newDisplay = withAuth { username => implicit request =>
    displayForm.bindFromRequest.fold(
      errors => BadRequest(views.html.displays(Display.all(), errors, xAppForms(Display.all()))),
      display => {
        Display.create(display.name, display.ip_screen_address)
        Redirect(routes.Displays.index)
      }
    )
  }

  def deleteDisplay(id: Long) = withAuth { username => implicit request =>
    Display.delete(id)
    Redirect(routes.Displays.index)
  }

  def runXApp(id: Long) = withAuth { username => implicit request => {
    val forms = xAppForms(Display.all())
    val runXAppForm = forms(id.toInt-1)
    runXAppForm.bindFromRequest.fold(
      errors => BadRequest(views.html.displays(Display.all(), displayForm, forms.map{form => if(runXAppForm == form) errors else form})),
      xAppName => {
        Redirect(routes.Displays.index)
      }

    )
    }
  }

}
