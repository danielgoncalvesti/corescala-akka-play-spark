package corescala
import scala.xml.NodeSeq.Empty
import scala.xml.NodeSeq

case class Funcionario(nome: String, salario: BigDecimal)

object Funcionario {
  def totalSalarios(funcionarios: List[Funcionario]): BigDecimal = {
       funcionarios.foldLeft(BigDecimal(0))((total, f) => total + f.salario)
  }
// Utilizando Map
def xmlComMap(funcionarios: List[Funcionario]): NodeSeq =
  <funcionarios>
    {funcionarios.map{f =>
        <funcionario salario={f.salario.toString}>{f.nome}</funcionario>
      }
    }
  </funcionarios>
// Utilizando FoldLeft
  def xml(funcionarios: List[Funcionario]): NodeSeq =
    <funcionarios>
      {funcionarios.foldLeft(Empty){(xml,f) =>
         xml :+ <funcionario salario={f.salario.toString}>{f.nome}</funcionario>
       // Ou  
       //xml ++ <funcionario salario={f.salario.toString}>{f.nome}</funcionario>
        }
      }
    </funcionarios>
}
