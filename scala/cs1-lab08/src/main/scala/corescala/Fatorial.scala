package corescala

object Fatorial {
  def fatorial(x: Int): Int = if (x == 1) 1 else x * fatorial(x - 1)
//def fatorial2(x: Int): Int = throw new UnsupportedOperationException("not implemented yet")
  def fatorial2(x: Int): Int = {
    x match {
      case 1 => 1
      case _ => x * fatorial(x - 1)
    }
  }
}
