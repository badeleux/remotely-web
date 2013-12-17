package controllers

import play.api._
import play.api.mvc._

import play.api.libs.ws.WS
import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits._
import java.util.concurrent.TimeoutException
import scala.sys.process._
import play.api.data.Form
import play.api.data.Forms._
import models.SSHLogin
import play.api.libs.json._
import play.api.libs.json.Json._

object Login extends Controller {

//  val loginForm = Form(
//    mapping(
//      "ip_address" -> nonEmptyText,
//      "login" -> nonEmptyText,
//      "password" -> nonEmptyText
//    )
//      ((ip_address, login, password) => {
//        print("ssh" + ip_address + "login: " + login)
//        SSHLogin(ip_address, login, password)
//      }
//        )
//      ((login: SSHLogin) =>
//        Option((login.ip, login.login, login.pass))
//        )
//  )

  def index = Action {
    Ok(views.html.login())
  }

  def signin() = Action.async(parse.json) {
    implicit request =>
    {
      var login : String = ""
      var f = Future[Boolean] {
        Logger.debug("jestem tutaj1")
        var sshIPAddress = (request.body \ "ip_address").asOpt[String]
        var someLogin = (request.body \ "login").asOpt[String]
        var password = (request.body \ "password").asOpt[String]
        if (sshIPAddress.isEmpty || someLogin.isEmpty || password.isEmpty) {
          false
        }
        else {
          Logger.debug("jestem tutaj")
          login = someLogin.get
          var sshLogin = SSHLogin(sshIPAddress.get, someLogin.get, password.get)
          sshLogin.connect()
        }
      }
      f.map(fut => {
        var res = Ok(toJson(Map("success" -> fut)))
        if (fut) {
          res.withSession(Security.username -> login)
        }
        else
          res
      })
    }
  }

  def logout() = Action {
    Redirect(routes.Login.index).withNewSession.flashing(
      "success" -> "You are now logged out."
    )
  }



}