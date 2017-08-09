package corescala

import akka.actor._

// class porque tem estado
case class Participante(nome:String)

// object pq nao tem estado
case object GetParticipantes

class ColetorParticipantes extends Actor {

  //var participantes:List[String] = List()
  import scala.collection.mutable._
  val participantes:ArrayBuffer[String] = ArrayBuffer()
  val enviadorEmails = 

  def receive = {
     //case Participante(nome) => participantes = participantes :+ nome
     case Participante(nome) => participantes += nome
     case GetParticipantes => {
         sender ! participantes.toList
     } 
  }

}

class EnviadorEmails extends Actor {
  def receive = {
     case EnviarEmail(email, msg) => println(s"Enviando:\n $msg \npara: $email")
  }

}
