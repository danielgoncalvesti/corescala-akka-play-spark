package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models._
import play.api.i18n.{I18nSupport, MessagesApi}
import javax.inject.Inject

class ItemController @Inject() (val messagesApi:MessagesApi) extends Controller with play.api.i18n.I18nSupport {

	val itemForm = Form ( 
		mapping (
				"id" -> longNumber,
				"nome" -> nonEmptyText
		) (Item.apply) (Item.unapply)
	)
	
	def index = Action {
		Ok(views.html.index(Item.lerTodos))
	}
	
	def novoItem = Action {
		Ok(views.html.itemForm(itemForm))
	}
	
	def salvarItem = Action { implicit request =>
		itemForm.bindFromRequest.fold(
			erros => BadRequest(views.html.itemForm(erros)),
			item => {
				Item.inserir(item)
				Redirect(routes.ItemController.index)
			}
		)
	}

}

import akka.actor._

class AtorExemplo extends Actor {
	def receive = {
		case msg => println(msg)
	}
}

class DemoController extends Controller {
	def index(visitas:Int) = Action {
		Ok(views.html.demos(visitas))
	}
	
	def atores = Action {
		import play.api.libs.concurrent.Akka
		import play.api.Play.current

		val ator = Akka.system.actorOf(Props[AtorExemplo], "meu-ator")
		ator ! "vai!"

	    Ok("Atores criados com sucesso")
	}
	
	def testeFormularios = Action {
	
		import play.api.data.Form
		import play.api.data.Forms._
		val criterioBuscaForm = Form ( "email" -> nonEmptyText)
	
		import scala.util.Try
		val criterio1 = Try { criterioBuscaForm.bind(Map("email" -> "gc@scala.com.br")).get } 
		val criterio2 = Try { criterioBuscaForm.bind(Map("nome" -> "gc@scala.com.br")).get }
		val criterio3 = Try { criterioBuscaForm.bind(Map("email" -> "")).get } 
		
		val loginForm = Form ( 
			tuple (
				"usuario" -> nonEmptyText,
				"senha" -> nonEmptyText
			)
		)
		
		Ok(s"Resultado 1 ${criterio1} \nResultado 2 $criterio2\nResultado 3 $criterio3")
		
	}

}


class SessionController extends Controller {
	def adiciona(nome:String,valor:String) = Action { implicit request =>
		Ok(s"atributo $nome adicionado na sessao com o valor $valor").addingToSession(nome -> valor)
		//Ok(s"Versao 2 - atributo $nome adicionado na sessao com o valor $valor").withSession(request.session + (nome,valor))
	}
	
	def lista = Action { request =>
		Ok(request.session.data.toString)
	}
	
	def remove(nome:String) = Action { implicit request =>
		Ok(s"Removendo atributo $nome da sessao").removingFromSession(nome)
		//Ok(s"Versao 2 - Removendo atributo $nome da sessao").withSession(request.session - nome)
	}
	
	def limpa = Action {
		Ok("Limpando todos os atributos da sessao").withNewSession
	}
}


class CookieController extends Controller {
	def salvaCookie = Action {
		Ok("Cookie salvo com sucesso").withCookies(Cookie("usuario","Globalcode"))
	}
	
	def leCookie = Action { request =>
		request.cookies.get("usuario")
						.map( cookie => Ok(s"Recebido valor ${cookie.value}") )
						.getOrElse( Ok("Nenhum valor de cookie encontrado"))
	}
	
	def removeCookie = Action {
		Ok("Cookie removido com sucesso").discardingCookies(DiscardingCookie("usuario"))
	}
}

class LivroController extends Controller {

  def find(isbn:String) = Action {
    Ok(s"Recebido valor $isbn")
  }
}

class ProdutoController extends Controller {
	import anorm._
	import play.api.db._
	import play.api.Play.current

	def insereProdutos = Action {
		val id = 1
		val nome = "produto1"
		val marca = "marca1"
		val id2 = 2
		val nome2 = "produto2"
		val marca2 = "marca2"
		var resultado1 = 0
		var resultado2 = 0
		DB.withConnection { implicit conn =>
			resultado1 = SQL("""
						INSERT INTO produto(id,nome,marca) VALUES ({id},{nome},{marca})
						""").on("id" -> id , "nome" -> nome, "marca" -> marca).executeUpdate()
			resultado2 = SQL"""
						INSERT INTO produto(id,nome,marca) VALUES ($id2, $nome2, $marca2)
						""".executeUpdate()
		}
		Ok(s"resultado1 = $resultado1 : resultado2 = $resultado2")							
	}

	def find(id:Int) = Action {
		Ok(s"Carregando produto com id $id")
	}
	
	def getByCodigo(cod:String) = Action {
		Ok(s"Carregando produto com codigo $cod")
	}
	
	def get(version:Option[String]) = Action {
		Ok(s"Utilizando produto com versao $version")
	}
	
	def list(page:Int) = Action {
		Ok(s"Listando a pagina $page")
	}
}

class FileController extends Controller {
	def load(path:String) = Action {
		Ok(s"Carregando arquivo em $path");
	}

}
