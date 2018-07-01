def values(fun: (Int => Int), low: Int, high: Int) = {
  for (i <- low to high) yield {
    (i, fun(i))
  }
}

values(x => x * x, -5, 5)

Array[Int](1, 2, 8, 4, 7, 5, 6).reduceLeft((x: Int, y: Int) => math.max(x, y))

def factorial(n: Int): Int = {
  (1 to n).reduceLeft((x, y) => x * y)
}
factorial(4)
factorial(5)
factorial(6)

def largest(fun: (Int => Int), inputs: Seq[Int]): Int = {
  inputs.map(fun).max
}
largest(x => 10 * x - x * x, 1 to 10)

def largestAt(fun: (Int => Int), inputs: Seq[Int]): Int = {
  inputs.maxBy(fun)
}
largestAt(x => 10 * x - x * x, 1 to 10)

val pairs = (1 to 10) zip (11 to 20)

def adjustToPair(fun: (Int, Int) => Int)(pair: (Int, Int)): Int = {
    fun(pair._1, pair._2)
}
adjustToPair(_ * _)((6, 7))
pairs.map(adjustToPair(_ + _))

val s = Array[String]("hello", "purple", "bananas")
val lengths = s.map(x => x.length)
s.corresponds(lengths)((x: String, y: Int) => x.length == y)

def unless(condition: => Boolean)(block: => Unit): Unit = {
  if (!condition) {
    block
  }
}

unless (1 == 0) {
  println("whoa")
}