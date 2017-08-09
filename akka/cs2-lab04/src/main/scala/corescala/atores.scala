package corescala

import akka.actor._
import java.util.concurrent.TimeoutException
import java.net.UnknownHostException
import akka.actor.SupervisorStrategy._
import akka.actor.OneForOneStrategy
import scala.concurrent.duration._
import scala.language.postfixOps

object ColetorParticipantes {
	case class Participante(nome:String,email:String)
	case object GetParticipantes
}

class ColetorParticipantes extends Actor {
	import scala.collection.mutable._
	import ColetorParticipantes._
	import EnviadorEmails._

        override val supervisorStrategy = OneForOneStrategy(maxNrOfRetries=10,
			withinTimeRange = 1 minute, loggingEnabled=false){
        		   case _:TimeoutException => Resume
                           case _:UnknownHostException => Restart
         }
	
	val participantes:ArrayBuffer[String] = ArrayBuffer()
	val enviadorEmails = context.actorOf(Props[EnviadorEmails], "enviadorEmails")
	
	def receive = {
		case Participante(nome,email) => 
			participantes += nome
			enviadorEmails ! EnviarEmail(email,s"$nome, bem vindo(a)!")
		
		case GetParticipantes => sender ! participantes.toList
	}
}

class EnviadorEmails extends Actor {
	import EnviadorEmails._

  	override def preStart() = {
            println("===============Iniciando o ator Enviador Emails===================")
        }

	def receive = {
		case EnviarEmail(email,msg) => 
                    if(email == "timeout") throw new TimeoutException("lancei Timeout Exception")
   		    else if(email == "desconhecido") throw new UnknownHostException("Unknown Host Exception")
                    else println(s"Enviando:\n $msg \npara: $email")
	}
}

object EnviadorEmails {
	case class EnviarEmail(email:String, msg:String)
}
