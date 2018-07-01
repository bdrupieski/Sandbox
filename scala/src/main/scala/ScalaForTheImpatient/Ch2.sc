val x = 2

val s = if (x > 0) 1 else -1

val whatType: Any = if (x > 0) "positive" else -1

if (x > 0) 1

if (x > 5) 1
if (x > 5) 1 else ()

val u: Unit = ()
val a: AnyVal = ()

import scala.runtime.BoxedUnit

a.getClass.getSimpleName
a.getClass.getMethods.foreach(x => println(x))

u == BoxedUnit.UNIT
u.getClass.getSimpleName
u.getClass.getMethods.foreach(x => println(x))

var r = 0
if (x > 0) { r = r * x; r -= x }
r

r = 0
if (x > 0) {
  r = r * x
  r -= x
}
r

val q = {
  val t1 = 2
  val t2 = 4
  t2 - t1
}

var n = 3
while (n > 0) {
  n -= 1
}
n

for (what <- 1 to 3) {
  n += what
}
n

var m = 0
for (what <- 1 until 5) {
  m += what
}
m

var o = ""
for (what <- "whatever") {
  o += what
}
o

for (i <- 1 to 3; j <- 5 to 7 if i != 2 && j != 6) yield {
  i.toString + j.toString
}

for (i <- 1 to 3; something = i + 1; j <- something to 4) yield {
  i.toString + j.toString
}

for {
  i <- 1 to 3
  j <- 1 to 3
  k <- 1 to 3
} yield i + j + k

def abs(x: Double) = if (x > 0) x else -x

abs(4)
abs(-5)

def factorial(n: Int): Int = {
  var r = 1
  for (i <- 1 to n) {
    r *= i
  }
  r
}

factorial(1)
factorial(2)
factorial(3)
factorial(4)
factorial(5)

def fac(n: Int): Int = { if (n <= 0) 1 else n * fac(n -1) }
fac(5)

def decorate(str: String, left: String = "[", right: String = "]"): String = {
  left + str + right
}

decorate("hello")
decorate("hello", "(")
decorate(left = "--->", str = "hello", right = "<---")

def sum(args: Int*): Int = {
  var result = 0
  for (arg <- args) {
    result += arg
  }
  result
}
val theSum = sum(1, 2, 3, 4, 5)
val theSum2 = sum(1 to 5: _*)

def box(s: String): Unit = {
  val border = "-" * s.length + "--" + sys.props("line.separator")
  println(border + "|" + s + "|" + sys.props("line.separator") + border)
}

box("WHOA")

lazy val lazilyInitialized = {
  println("executed!")
  4
}

lazilyInitialized

val whatisThis = if (x > 0) math.sqrt(x) else throw new IllegalArgumentException("oops")