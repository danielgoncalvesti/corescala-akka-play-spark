package corescala

case class Aluno(id: Int, nome: String, idade: Int, notas: List[Int], media: Option[Double])

object Alunos {
  def encontraMaiores(alunos: List[Aluno]): List[Aluno] = {
         alunos.filter(x => x.idade >= 18)
  } 

  def nomes(alunos: List[Aluno]): List[String] = {
         alunos.map(x => x.nome)
  }

  // na implementação da função abaixo, use as funções acima como base
  def nomesMaiores(alunos: List[Aluno]): List[String] = {
         nomes(encontraMaiores(alunos))
  }


  def calculaMedias(alunos: List[Aluno]): List[Aluno] = alunos.map(a => {
     if(a.notas.size == 3) {
        val novaMedia = trunc(a.notas.sum.toDouble/a.notas.size)
        a.copy(media=Option(novaMedia)) // ou fazer assim Aluno(a.id, a.nome, a.idade, Option(novaMedia))
     } else {
        throw new IllegalStateException(s"${a.nome} nao tem a quantidade certa de notas, notas encontradas: ${a.notas.mkString(", ")}")
     }    
  })

    
  // use esta função para evitar dizimas periódicas
  def trunc(x: Double) = math.round(x * 100) * 0.01
}
