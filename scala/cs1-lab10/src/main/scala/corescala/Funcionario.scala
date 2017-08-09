package corescala

object Utils {
//  def aumentar(porcentagem: BigDecimal, v: BigDecimal): BigDecimal = throw new UnsupportedOperationException("not implemented yet")

 def aumentar(porcentagem: BigDecimal)(v: BigDecimal): BigDecimal = v+(porcentagem/100)*v
 def aumentar10 = aumentar(10) _
 def aumentar20: BigDecimal => BigDecimal = aumentar(20)
 def aumentar30: BigDecimal => BigDecimal = aumentar(30)

}

case class Funcionario(salario: BigDecimal) {
  
  def aumentarSalario(f: BigDecimal => BigDecimal): Funcionario = {
     val novoSalario = f(salario)
     Funcionario(novoSalario)
  }


}
