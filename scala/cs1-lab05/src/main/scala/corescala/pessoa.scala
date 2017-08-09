package corescala

abstract class Pessoa(nome: String) {
  def numDocumento: String
}

class PessoaFisica(val nome: String, val cpf: String) extends Pessoa(nome){
  def numDocumento: String = cpf
  override def toString():String = s"PessoaFisica($nome, $cpf)" 
}

class PessoaJuridica(val nome: String, val cnpj: String) extends Pessoa(nome){
  def numDocumento: String = cnpj
  override def toString():String = s"PessoaJuridica($nome, $cnpj)"
}

object CriadorPessoas{
  def criarPF(nome: String, cpf: String) = new PessoaFisica(nome, cpf)

  def criarPJ(nome: String, cnpj: String) = new PessoaJuridica(nome, cnpj)
  


}
