
val operando1 = args(0).toInt
val operando2 = args(2).toInt
val operador = args(1)

val result = operador match {
  case "+" => Calculadora.soma(operando1, operando2)
  case "-" => Calculadora.subtrai(operando1, operando2)
  case "*" => Calculadora.multiplica(operando1, operando2)
  case "/" => Calculadora.divide(operando1, operando2)
  case "%" => Calculadora.modulo(operando1, operando2)
}

println("resultado da conta: " + result)


object Calculadora {
  def soma(x: BigDecimal, y: BigDecimal) = x + y
  def subtrai(x: BigDecimal, y: BigDecimal) = x - y
  def multiplica(x: BigDecimal, y: BigDecimal) = x * y
  def divide(x: BigDecimal, y: BigDecimal) = x / y
  def modulo(x: BigDecimal, y: BigDecimal) = x % y
  
  def + = soma _
  def - = subtrai _
  def * = multiplica _
  def / = divide _
  def % = modulo _
}

