# Trabajo de Consulta: Integración Numérica con Scala (Método de Simpson 1/3)

**Asignatura:** Programación Funcional y Reactiva  
**Carrera:** Computación  
**Lenguaje:** Scala  
**Paradigma:** Programación funcional  
**Tema:** Funciones de orden superior e integración numérica (método de Simpson 1/3)

---

## 1. Introducción

En este trabajo se implementa, en el lenguaje Scala, una solución funcional para aproximar integrales definidas mediante el método numérico de Simpson 1/3, y se calcula el error de aproximación respecto a valores de referencia teóricos.

El objetivo principal es poner en práctica la programación funcional en Scala:

- Utilizando funciones de orden superior (higher-order functions),
- Evitando bucles imperativos y variables mutables,
- Trabajando con colecciones inmutables y funciones puras.

---

## 2. Objetivos

### 2.1 Objetivo general

Implementar una función genérica de integración numérica en Scala, basada en el método de Simpson 1/3, que reciba como parámetro la función a integrar y permita aproximar diferentes integrales definidas, calculando el error de aproximación para cada caso.

### 2.2 Objetivos específicos

- Implementar una función de orden superior `integracion` en Scala, que reciba una función `f(x)` y los límites `a` y `b`.
- Implementar una función `errorAprox` que calcule el error absoluto entre el valor esperado y el valor aproximado.
- Definir en Scala las funciones que representan a las integrandas de las diferentes integrales propuestas en la guía de trabajo.
- Comparar los resultados numéricos obtenidos con los valores de referencia y analizar el error.

---

## 3. Fundamento teórico

### 3.1 Programación funcional y funciones de orden superior

En programación funcional:

- Las funciones se tratan como valores de primera clase.
- Es posible pasar funciones como parámetros y devolver funciones como resultado.
- Una función de orden superior (higher-order function) es aquella que recibe una o más funciones como argumentos y/o devuelve una función.

En este trabajo, la función `integracion` es una función de orden superior porque recibe como parámetro `f: Double => Double`, es decir, una función que va de `Double` a `Double` y representa la integranda `f(x)`.

### 3.2 Método de Simpson 1/3

El método de Simpson 1/3 permite aproximar el valor de una integral definida mediante la fórmula:

    Integral ≈ (b - a) / 6 * ( f(a) + 4 * f( (a + b) / 2 ) + f(b) )

donde:

- `a` y `b` son los límites de integración,
- `(a + b) / 2` es el punto medio del intervalo,
- `f(a)`, `f( (a + b)/2 )` y `f(b)` son los valores de la función en esos puntos.

Este método se basa en aproximar la función por un polinomio de segundo grado (parábola) que interpola los puntos:

- `(a, f(a))`
- `((a + b)/2, f( (a + b)/2 ))`
- `(b, f(b))`.

### 3.3 Error absoluto de aproximación

Para evaluar la calidad de la aproximación se usa el error absoluto:

    error = | valorEsperado - valorObtenido |

donde:

- `valorEsperado` es el valor de referencia (por ejemplo, el valor exacto de la integral o un valor dado en la guía).
- `valorObtenido` es el resultado numérico retornado por el programa.

---

## 4. Desarrollo de la solución en Scala

A continuación se muestra la implementación de las funciones principales y la forma de reutilizarlas para diferentes integrales.

### 4.1 Función genérica `integracion`

    def integracion(a: Double, b: Double, f: Double => Double): Double = {
      val xMedio: Double = (a + b) / 2.0
      val fa: Double = f(a)
      val fm: Double = f(xMedio)
      val fb: Double = f(b)
      val resultado: Double = (b - a) * (fa + 4.0 * fm + fb) / 6.0
      resultado
    }

**Descripción:**

- `a`: límite inferior de integración.  
- `b`: límite superior de integración.  
- `f`: función integranda `f(x)`, representada como `Double => Double`.

La función calcula el valor aproximado de la integral usando la fórmula de Simpson 1/3.

Esta función es reutilizable: solo se modifica `f`, `a` y `b` para aproximar distintas integrales.

### 4.2 Función `errorAprox`

    def errorAprox(valorEsperado: Double, valorObtenido: Double): Double = {
      val diferencia: Double = valorEsperado - valorObtenido
      val errorAbs: Double = math.abs(diferencia)
      errorAbs
    }

**Descripción:**

- Calcula el error absoluto entre el valor de referencia (`valorEsperado`) y el valor aproximado (`valorObtenido`).
- Facilita comparar el comportamiento del método numérico frente a los valores teóricos.

### 4.3 Definición de las funciones integrandas

Para cada integral propuesta en la guía, se define una función en Scala. Por ejemplo:

    // Integral 1: ∫_3^5 (-x^2 + 8x - 12) dx
    def f1(x: Double): Double = {
      val y: Double = -x * x + 8.0 * x - 12.0
      y
    }

    // Integral 2: ∫_0^2 3x^2 dx
    def f2(x: Double): Double = {
      val y: Double = 3.0 * x * x
      y
    }

    // Integral 3: ∫_-1^1 (x + 2x^2 - x^3 + 5x^4) dx
    def f3(x: Double): Double = {
      val y: Double = x + 2.0 * x * x - x * x * x + 5.0 * math.pow(x, 4.0)
      y
    }

    // … (y así sucesivamente para f4, f5, f6, f7)

Cada función se utiliza luego como argumento de `integracion`.

### 4.4 Cálculo de las aproximaciones y de los errores

Ejemplo para las dos primeras integrales (en el código del repositorio se incluyen las siete):

    // Integral 1
    val aprox1: Double = integracion(3.0, 5.0, f1)
    val esperado1: Double = 7.33      // valor de referencia de la guía
    val error1: Double = errorAprox(esperado1, aprox1)

    // Integral 2
    val aprox2: Double = integracion(0.0, 2.0, f2)
    val esperado2: Double = 8.0
    val error2: Double = errorAprox(esperado2, aprox2)

Este mismo patrón se repite para todas las integrales:

1. Definir `fX`.  
2. Calcular `aproxX = integracion(a, b, fX)`.  
3. Utilizar `errorAprox(esperadoX, aproxX)` para obtener el error.

---

## 5. Resultados

Los resultados del programa consisten, para cada integral, en:

- El valor aproximado obtenido por el método de Simpson 1/3.
- El valor teórico o de referencia proporcionado en la guía.
- El error absoluto entre ambos valores.

| Integral | Intervalo | f(x)                              | Aproximación (programa) | Valor esperado (guía) | Error absoluto |
|---------|-----------|------------------------------------|-------------------------|------------------------|----------------|
| I₁      | [3, 5]    | -x² + 8x - 12                     | 7.333333333333333       | 7.33                   | 0.003333333333329662 |
| I₂      | [0, 2]    | 3x²                               | 8.0                     | 8.0                    | 0.0            |
| I₃      | [-1, 1]   | x + 2x² − x³ + 5x⁴                | 4.666666666666667       | 3.333                  | 1.3336666666666668 |
| I₄      | [1, 2]    | (2x + 1) / (x² + x)               | 1.0999999999999999      | 1.09861                | 0.0013899999999997803 |
| I₅      | [0, 1]    | eˣ                                | 1.7188611518765928      | 1.71828                | 5.811518765928003E-4 |
| I₆      | [2, 3]    | 1 / (x − 1)                       | 0.6944444444444443      | 0.828427               | 0.13398255555555572 |
| I₇      | [0, 1]    | 1 / (1 + x²)                      | 0.7833333333333333      | 0.785398               | 0.0020646666666667146 |

---

## 6. Conclusiones

- La función `integracion` implementada en Scala demuestra cómo se puede expresar un método numérico clásico (Simpson 1/3) dentro del paradigma funcional, utilizando una función de orden superior que recibe la integranda como parámetro.
- La solución aprovecha las capacidades de Scala para trabajar con funciones como valores, permitiendo reutilizar el mismo código para múltiples integrales simplemente cambiando la función `f` y los límites `a` y `b`.
- El cálculo del error de aproximación permite evaluar la calidad de las soluciones numéricas y verificar que el método de Simpson 1/3 ofrece resultados coherentes con los valores de referencia.
