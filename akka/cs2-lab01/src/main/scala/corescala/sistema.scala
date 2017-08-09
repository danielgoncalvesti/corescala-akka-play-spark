package corescala

import akka.actor._

object SistemaColetor extends App{

  val system = ActorSystem("Sistema-Coletor")
  val coletor = system.actorOf(Props[ColetorParticipantes])

  coletor ! Participante("Daniel")
  coletor ! Participante("Jose")
  coletor ! Participante("Maria")
  coletor ! Participante("Juliana")
  coletor ! Participante("Paula")
  coletor ! Participante("Chielo")

  coletor ! ImprimaParticipantes

  coletor ! Participante("1")
  coletor ! Participante("2")
  coletor ! Participante("3")
  coletor ! Participante("3")

  coletor ! ImprimaParticipantes

  system.stop(coletor)
  system.terminate()

}
