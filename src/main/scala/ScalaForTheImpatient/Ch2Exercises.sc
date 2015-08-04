def signum(i: Int): Int = if (i > 0) 1 else if (i < 0) -1 else 0

signum(42)
signum(0)
signum(-42)

val emptyBlockType = {}
emptyBlockType == ()

var x: Unit = ()
var y: Int = 0

x = y = 1

for (i <- 10 to 1 by -1) println(i)

def countdown(n: Int) {
  for (i <- n to 0 by -1) println(i)
}

countdown(4)

def unicodeProduct(s: String): Long = {
  var product = 1L
  for (i <- s) {
    product *= i.toInt
  }
  product
}

unicodeProduct("Hello")
"Hello".map(_.toLong).product
"Hello".foldLeft(1L)((x, y) => x * y)

def unicodeProductRecursive(s: String): Long = {
  if (s.length == 0) {
    1
  } else {
    s.head.toLong * unicodeProductRecursive(s.tail)
  }
}

unicodeProductRecursive("Hello")

def isPositive(i: Int) = i > 0
def isNegative(i: Int) = i <= 0
def isEven(i: Int) = i % 2 == 0
def isOdd(i: Int) = i % 2 == 1

def myPow(x: Int, n: Int): Int = {
  if (isEven(n) && isPositive(n)) {
    val e = n / 2
    myPow(x, e) * myPow(x, e)
  } else if (isOdd(n) && isPositive(n)) {
    x * myPow(x, n - 1)
  } else if (n == 0) {
    1
  } else {
    1 / myPow(x, -n)
  }
}

myPow(2, 8)
math.pow(2, 8)
