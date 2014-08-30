package models

import scala.slick.driver.H2Driver.simple._
import scala.slick.jdbc.meta.MTable

case class PersonTable(tag: Tag) extends Table[Person](tag,"PERSON"){
  def id = column[Int]("PERSON_ID",O.PrimaryKey,O.AutoInc)
  def name = column[String]("PERSON_NAME")

  override def * = (id.?, name)<>(Person.tupled,Person.unapply)
}

object DB{
  val db = Database.forURL("jdbc:h2:mem:play",driver="org.h2.Driver")
  val personQuery = TableQuery[PersonTable]

  def init = {
    db.withSession{ implicit session =>
      if(MTable.getTables("PERSON").list.isEmpty)
        personQuery.ddl.create
    }
  }

  def save(person: Person) = {
    db withSession { implicit Session =>
      personQuery.insert(person)
    }
  }

  def loadPersons = {
    db withSession{ implicit Session =>
      personQuery.list
    }
  }
}
