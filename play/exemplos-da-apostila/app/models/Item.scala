package models

import play.api.db._
import play.api.Play.current
import anorm._
import anorm.SqlParser._
import scala.language.postfixOps

case class Item(id:Long, nome:String)

object Item {
	val itemParser = long("id") ~ str("nome") map {
		case id~nome => Item(id,nome)
	}

	def inserir(item: Item) =
		DB.withConnection { implicit conn =>
			SQL"""
				INSERT INTO item(nome) VALUES (${item.nome})
			""".executeUpdate()
	}
	
	def lerTodos = DB.withConnection { implicit conn =>
		SQL"SELECT * FROM item".as(itemParser *)
	}
}