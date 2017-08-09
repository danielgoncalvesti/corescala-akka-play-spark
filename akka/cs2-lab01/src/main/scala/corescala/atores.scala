package corescala

import akka.actor._

// class porque tem estado
case class Participante(nome:String)

// object pq nao tem estado
case object ImprimaParticipantes

class ColetorParticipantes extends Actor {

  import scala.collection.mutable._
  
//var participantes:List[String] = List()
  val participantes:ArrayBuffer[String] = ArrayBuffer()


  def receive = {
     //case Participante(nome) => participantes = participantes :+ nome
     case Participante(nome) => participantes += nome
     case ImprimaParticipantes => {
         participantes.foreach(x => println(x))
     } 
  }

}
