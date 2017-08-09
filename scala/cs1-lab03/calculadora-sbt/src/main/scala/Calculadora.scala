class Calculadora{

	def soma(x: BigDecimal, y: BigDecimal): BigDecimal =  x+y

	def subtrai(x: BigDecimal, y: BigDecimal): BigDecimal = x-y

	def multiplica(x:BigDecimal, y: BigDecimal): BigDecimal = x*y

	def divide(x:BigDecimal, y: BigDecimal): BigDecimal = x/y

	def modulo(x:BigDecimal, y: BigDecimal): BigDecimal = x%y

	def + = soma _

	def - = subtrai _

	def * = multiplica _

	def / = divide _

	def % = modulo _
}

/*
 def x:BigDecimal = args(0).toDouble
 def y:BigDecimal = args(1).toDouble
 val operacao = args(2)

 val calc = new Calculadora()
 var resultado:BigDecimal = BigDecimal(0.toDouble)


operacao match {
 case "+" =>  resultado = calc.+(x,y)
 case "-" =>  resultado = calc.-(x,y)
 case "*" =>  resultado = calc.*(x,y)
 case "/" =>  resultado = calc./(x,y)
 case "%" =>  resultado = calc.%(x,y)
}

println(resultado)
*/
