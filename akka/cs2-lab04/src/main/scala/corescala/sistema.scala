package corescala

import akka.actor._
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.language.postfixOps

//import das mensagens
import corescala.ColetorParticipantes._

object SistemaColetor extends App {
  val system = ActorSystem("sistema-coletor")
  val coletor = system.actorOf(Props[ColetorParticipantes],"coletor")
  implicit val timeout = Timeout(5 seconds)
  
  coletor ! Participante("Joao","joao@corescala.com.br")
  coletor ! Participante("Maria","maria@corescala.com.br")
  //coletor ! Participante("Jcranky","timeout")
  coletor ! Participante("Jcranky","desconhecido")
  coletor ! Participante("Jose","jose@corescala.com.br")
  
  val resposta1 = coletor ? GetParticipantes

  resposta1 onSuccess {
	case lista:Seq[_] => {
		println("Imprimindo lista1")
		lista foreach println
	}
  }
  Thread.sleep(500)
  /*
  coletor ! Participante("Eduardo","eduardo@corescala.com.br")
  coletor ! Participante("Monica","monica@corescala.com.br")
  
  val resposta2 = coletor ? GetParticipantes 
  
  resposta2 onSuccess {
	case lista:Seq[_] => {
		println("Imprimindo lista2")
		lista foreach println
	}
  } 
  */
  system.stop(coletor)
  system.terminate()
}
