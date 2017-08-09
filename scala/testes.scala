def soma(x:Int, y:Int):Int = x+y
// sem currying
def processaInteiros(x:Int, y:Int, f:(Int, Int)=>Int):Unit ={
  require(x>0, s"$x deve ser maior do que zero")
  require(y>0, s"$y deve ser maior do que zero")
  val resultado = f(x,y)
  println(s"resultado = $resultado")
}
// sem currying
def adicionaValidacaoLogging(f:(Int, Int)=>Int):(Int, Int)=>Unit = {
  (x,y) => {
    require(x>0, s"$x deve ser maior do que zero")
    require(y>0, s"$y deve ser maior do que zero")
    val resultado = f(x,y)
    println(s"resultado = $resultado")
  }
}
// utilizando o currying
def processaInteiros2(f:(Int,Int)=>Int)(x:Int,y:Int):Unit = {
    require(x>0, s"$x deve ser maior do que zero")
    require(y>0, s"$y deve ser maior do que zero")
    val resultado = f(x,y)
    println(s"resultado = $resultado")
}
