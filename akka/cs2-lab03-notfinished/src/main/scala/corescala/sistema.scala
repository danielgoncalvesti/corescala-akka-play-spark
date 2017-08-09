package corescala

import akka.actor._
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.concurrent.ExecutionContext.Implicits.global

object SistemaColetor extends App{

  val system = ActorSystem("Sistema-Coletor")
  val coletor = system.actorOf(Props[ColetorParticipantes])
  implicit val timeout = Timeout(5 seconds)



  coletor ! Participante("Daniel")
  coletor ! Participante("Jose")
  coletor ! Participante("Maria")
  coletor ! Participante("Juliana")
  coletor ! Participante("Paula")
  coletor ! Participante("Chielo")

  val recebendo = coletor ? GetParticipantes

  recebendo onSuccess {
	case lista:Seq[_] => {
		println("Imprimindo: ")
		lista foreach println
	}
  }

  
  system.stop(coletor)

  Thread.sleep(1)
  system.terminate()

}
