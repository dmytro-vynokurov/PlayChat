package controllers

import models.{DB, Person}
import play.api._
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json
import play.api.mvc._

object Application extends Controller {
  DB.init

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  val personForm: Form[Person] = Form {
    mapping(
      "id" -> optional(number),
      "name" -> text
    )(Person.apply)(Person.unapply)
  }

  def addPerson() = Action{implicit request =>
    val person = personForm.bindFromRequest.get
    DB.save(person)
    Redirect(routes.Application.index)
  }

  def getPersons() = Action{
    val persons = DB.loadPersons
    Ok(Json.toJson(persons))
  }

  def logIn() = play.mvc.Results.TODO
}