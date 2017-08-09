package corescala

import scala.xml.NodeSeq

class Container(app: WebApp) {
  implicit private val request = new Request(1)

//def run(page: String): String = page match {
//  case "index" => app.index(request)
//  case "about" => app.about(request)
//  case "contato" => app.contato(request)
//  case p => "pagina desconhecida"
//}

  def run(page: String): String = page match {
    case "index" => app.index    
    case "about" => app.about
    case "contato" => app.contato
    case p => "pagina desconhecida"
  }
}

object Container {

   implicit def strToNodeSeq(s: String): NodeSeq =
     <html><body>{s}</body></html>
   
}

