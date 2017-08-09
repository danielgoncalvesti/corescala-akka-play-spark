package corescala

case class Aluno(id: Int, nome: String, email: String, idade: Int)

class AlunoDAO {
  // nosso "banco de dados"
  private val alunos = List(
    Aluno(1, "paulo", "p@p.com", 13),
    Aluno(2, "joao", "j@j.com", 14),
    Aluno(3, "maria", "m@m.com", 15),
    Aluno(4, "jose", "jo@jo.com", 19)
  )

//  def findAlunoByEmail(email: String): Aluno = alunos.filter(a => a.email == email)(0)

// Primeira forma
//def findAlunoByEmail(email: String): Option[Aluno] = { 
//   val list = alunos.filter(a => a.email == email)
//   if (list.isEmpty){
//      None
//   } else{
//      Option(list(0))
//   }
//}

// Segunda forma
//def findAlunoByEmail(email: String): Option[Aluno] =  alunos.filter(a => a.email == email).headOption

// Primeira forma   
  def findAlunoByEmail(email: String): Option[Aluno] =  alunos.find(a => a.email == email)

}
