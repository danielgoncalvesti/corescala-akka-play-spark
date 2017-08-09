package corescala

import org.specs2.mutable.Specification

class PessoasSpec extends Specification {
  "pessoa fisica" >> {
//    val pf = new PessoaFisica("joao", "12312312312")
      val pf = CriadorPessoas.criarPF("joao", "12312312312")
    

    "ter cpf correto" >> {
      pf.cpf must_== "12312312312"
    }

    "ter nome correto" >> {
      pf.nome must_== "joao"
    }
  }

  "pessoa juridica" >> {
//   val pj = new PessoaJuridica("joao", "24142412410001")
     val pj = CriadorPessoas.criarPJ("joao", "24142412410001")
    "ter cnpj correto" >> {
      pj.cnpj must_== "24142412410001"
    }

    "ter nome correto" >> {
      pj.nome must_== "joao"
    }
  }

  "trabalhando com listas de pessoas" >> {
//    val pessoas = List(
//      new PessoaFisica("joao", "123"),
//      new PessoaJuridica("globalcode", "321435"),
//      new PessoaFisica("paulo", "321"))

    val pessoas = List(
      CriadorPessoas.criarPF("joao", "123"),
      CriadorPessoas.criarPJ("globalcode", "321435"),
      CriadorPessoas.criarPF("paulo", "321"))

    "ter o tamanho da lista de pessoas igual a 3" >> {
      pessoas.length must_== 3
    }

    "ter o documento correto" >> {
      pessoas(0).numDocumento must_== "123"
      pessoas(1).numDocumento must_== "321435"
    }
  }
}
