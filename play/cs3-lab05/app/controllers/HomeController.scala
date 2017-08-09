package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import scala.collection._
import play.api.data._
import play.api.data.Forms._

case class Participante(nome: String, email: String)

class HomeController extends Controller {

  //val participantes = List(Participante("Daniel", "daniel@gmail.com"), Participante("Foo", "foo@gmail.com"))
  var participantesSeq: Seq[Participante] = Seq.empty
	
  val participanteForm = Form (
     mapping (
         "nome" -> nonEmptyText,
         "email" -> nonEmptyText
     )(Participante.apply)(Participante.unapply)
  )

  def salvarParticipante = Action { implicit request =>
   participanteForm.bindFromRequest.fold(
	erros => BadRequest(views.html.newParticipante("Adicionar Novo Participante", erros)),
	participante => { 
		participantesSeq = participantesSeq :+ participante
		Redirect(routes.HomeController.listarParticipanteHtml)
        }
   )
  }

  def novoParticipante = Action {
   Ok(views.html.newParticipante("Adicionar Novo Participante", participanteForm))
  }

  def index = Action { implicit request =>
    val visitas = request.session.get("visitas").map(x => x.toInt + 1).getOrElse(1)
    
    Ok(s"Número de visitas: $visitas").withSession("visitas" -> visitas.toString)
  }
 
  def teste = Action {
   Ok("<html><h1>Olá Mundo!</h1></html>").as(HTML)
  }

  def salvaCookie = TODO
  
  def listarParticipantes = Action {
    Ok(participantesSeq.toString)
  }

  def listarParticipanteHtml = Action {
    Ok(views.html.participantes(participantesSeq))
  }
 
  def todo = TODO

  def redirect = Action {
   Redirect(routes.HomeController.index)
  }
  

}
