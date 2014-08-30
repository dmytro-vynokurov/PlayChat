package models

import play.api.libs.json.Json


case class Person(id: Option[Int],name: String)
object Person{
  implicit val personFormat = Json.format[Person]
  def tupled = {tuple:((Option[Int],String))=>Person(tuple._1,tuple._2)}
}

