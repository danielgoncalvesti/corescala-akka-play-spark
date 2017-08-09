package corescala

import akka.actor._
import java.util.concurrent.TimeoutException
import java.net.UnknownHostException

object ColetorParticipantes {
	case class Participante(nome:String,email:String)
	case object GetParticipantes
}

class ColetorParticipantes extends Actor {
	import scala.collection.mutable._
	import ColetorParticipantes._
	import EnviadorEmails._
	import akka.actor.SupervisorStrategy._		
	import scala.concurrent.duration._			
	import scala.language.postfixOps
	import akka.routing._
	
	override val supervisorStrategy = OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute) {
		case _:TimeoutException => Resume
		case _:UnknownHostException => Restart
	}
	
	val participantes:ArrayBuffer[String] = ArrayBuffer()
	//val enviadorEmails = context.actorOf(RoundRobinPool(5).props(Props[EnviadorEmails]),"enviadorEmails")
	val enviadorEmails = context.actorOf(RandomPool(5).props(Props[EnviadorEmails]),"enviadorEmails")
	def receive = {
		case Participante(nome,email) => 
			participantes += nome
			enviadorEmails ! EnviarEmail(email,s"$nome, bem vindo(a)!")
		
		case GetParticipantes => sender ! participantes.toList
	}
}

class EnviadorEmails extends Actor {
	import EnviadorEmails._

	def receive = {
		case EnviarEmail(email,msg) => 
			//if(email == "timeout") throw new TimeoutException("timeout no enviador de emails")
			//else if(email == "desconhecido") throw new UnknownHostException("host desconhecido no enviador de emails")
			println(s"Ator: ${self.path} Enviando: \n\t$msg \n\tpara: $email")
	}
	
	override def preStart = println("========= Iniciando EnviadorEmails ===========") 
}

object EnviadorEmails {
	case class EnviarEmail(email:String, msg:String)
}
