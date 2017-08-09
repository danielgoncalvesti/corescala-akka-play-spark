package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current
import scala.language.postfixOps
import play.api.db.DB

case class Participante(nome: String, email: String) 

object Participantes {
   val participanteParse = str("nome") ~ str("email") map {
       case nome~email => Participante(nome,email)
   }

   def salvar(part: Participante): Unit =
       DB.withConnection("participantes") { implicit conn =>
          SQL""" 
                INSERT INTO participantes(nome, email) VALUES (${part.nome}, ${part.email}) 
           """.executeUpdate()
   }

   def listar: List[Participante] = 
       DB.withConnection("participantes") { implicit conn =>
           SQL"SELECT * FROM participantes".as(participanteParse *)
   }
}
