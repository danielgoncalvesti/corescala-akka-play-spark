import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class CalcSpec extends Specification {
		
	"minha calculadora" >> {
	  "subtrair valores" >> new CalcScope{
	   calc.subtrai(2,1) must_== 1
	  }
	  
	  "somar valores" >> new CalcScope{
	    calc.soma(4,4) must_==8
	  }
	  
	  "dividir valores" >> new CalcScope{
	    calc.divide(10,2) must_==5
	  }
	  
	  "multiplicar valores" >> new CalcScope{
	    calc.multiplica(5,7) must_==35
	  }
	  
	  "resto da divisao" >> new CalcScope{
	    calc.modulo(3,3) must_==0
	  }
	}

	trait CalcScope extends Scope {
	  val calc = new Calculadora()
	}
}
