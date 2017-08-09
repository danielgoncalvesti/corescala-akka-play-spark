package corescala

case class Aluno(nome: String, email: String) 

class AlunoXML(alunoDAO: AlunoDAO) extends AlunoLog{
  def alunos = {
   log("[Logging] imprimindo XML COM alunos: ")
    val alunos = alunoDAO.findAll
    <alunos>
      {alunos.map{ aluno =>
        <aluno email={aluno.email}>{aluno.nome}</aluno>
      }}
    </alunos>
  }
}

class AlunoDAO{
  def findAluno(name: String): Option[Aluno] = throw new UnsupportedOperationException("not implemented yet")
  def findAll: List[Aluno] = throw new UnsupportedOperationException("not implemented yet")
}


trait AlunoLog{
 def log(msg: String): Unit = println(msg)
}
