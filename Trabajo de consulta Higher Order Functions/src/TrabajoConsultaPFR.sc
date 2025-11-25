def integracion(a: Double, b: Double, f: Double => Double): Double = {
  val xMedio: Double = (a + b) / 2.0
  val fa: Double = f(a)
  val fm: Double = f(xMedio)
  val fb: Double = f(b)
  val resultado: Double = (b - a) * (fa + 4.0 * fm + fb) / 6.0
  resultado
}



def errorAprox(valorEsperado: Double, valorObtenido: Double): Double = {
  val diferencia: Double = valorEsperado - valorObtenido
  val errorAbs: Double = math.abs(diferencia)
  errorAbs
}



def f1(x: Double): Double = {
  val y: Double = -x * x + 8.0 * x - 12.0
  y
}
val aprox1: Double = integracion(3.0, 5.0, f1)
val esperado1: Double = 7.33
val error1: Double = errorAprox(esperado1, aprox1)


def f2(x: Double): Double = {
  val y: Double = 3.0 * x * x
  y
}
val aprox2: Double = integracion(0.0, 2.0, f2)
val esperado2: Double = 8.0
val error2: Double = errorAprox(esperado2, aprox2)


def f3(x: Double): Double = {
  val y: Double = x + 2.0 * x * x - x * x * x + 5.0 * math.pow(x, 4.0)
  y
}
val aprox3: Double = integracion(-1.0, 1.0, f3)
val esperado3: Double = 3.333
val error3: Double = errorAprox(esperado3, aprox3)


def f4(x: Double): Double = {
  val numerador: Double = 2.0 * x + 1.0
  val denominador: Double = x * x + x
  val y: Double = numerador / denominador
  y
}
val aprox4: Double = integracion(1.0, 2.0, f4)
val esperado4: Double = 1.09861
val error4: Double = errorAprox(esperado4, aprox4)


def f5(x: Double): Double = {
  val y: Double = math.exp(x)
  y
}
val aprox5: Double = integracion(0.0, 1.0, f5)
val esperado5: Double = 1.71828
val error5: Double = errorAprox(esperado5, aprox5)


def f6(x: Double): Double = {
  val y: Double = 1.0 / math.sqrt(x - 1.0)
  y
}
val aprox6: Double = integracion(2.0, 3.0, f6)
val esperado6: Double = 0.828427  
val error6: Double = errorAprox(esperado6, aprox6)


def f7(x: Double): Double = {
  val y: Double = 1.0 / (1.0 + x * x)
  y
}
val aprox7: Double = integracion(0.0, 1.0, f7)
val esperado7: Double = 0.785398
val error7: Double = errorAprox(esperado7, aprox7)

