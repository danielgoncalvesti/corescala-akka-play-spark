package corescala

import akka.actor._

//import das mensagens
import corescala.ColetorParticipantes._

object SistemaColetor extends App {
  val system = ActorSystem("sistema-coletor")
  val coletor = system.actorOf(Props[ColetorParticipantes],"coletor")
  
  coletor ! Participante("Joao","timeout")
  coletor ! Participante("Maria","maria@corescala.com.br")
  
  coletor ! Participante("Jcranky","desconhecido")
  coletor ! Participante("Jose","jose@corescala.com.br")
  
  Thread.sleep(1000)
  system.stop(coletor)
  system.terminate()
}
