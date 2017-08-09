package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import scala.collection._
/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
case class Participante(nome: String, email: String)

class HomeController extends Controller {
  val participantes = List(Participante("Daniel", "daniel@gmail.com"), Participante("Foo", "foo@gmail.com"))
  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action { implicit request =>
    val visitas = request.session.get("visitas").map(x => x.toInt + 1).getOrElse(1)
    
    Ok(s"Número de visitas: $visitas").withSession("visitas" -> visitas.toString)
  }
 
  def teste = Action {
   Ok("<html><h1>Olá Mundo!</h1></html>").as(HTML)
  }

  def salvaCookie = TODO
  
  def listarParticipantes = Action {
    Ok(participantes.toString)
  }

  def listarParticipante = Action {
        Ok(views.html.participantes(participantes))
  }

  def salvarParticipante = TODO
  

  def novoParticipante = TODO
 
  def todo = TODO

  def redirect = Action {
   Redirect(routes.HomeController.index)
  }
  

}
