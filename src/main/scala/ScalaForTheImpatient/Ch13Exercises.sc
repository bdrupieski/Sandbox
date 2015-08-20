def indexes(input: String): Map[Char, Set[Int]] = {
  input.zipWithIndex.groupBy(x => x._1).map(x => x._1 -> x._2.map(x => x._2).toSet)
}
indexes("Mississippi")

def filterOutZero(input: List[Int]): List[Int] = {
  input.filter(x => x != 0)
}
filterOutZero(List(1, 2, 3, 0, 4, 5, 6, 0))

def getValues(names: Seq[String], namesToValues: Map[String, Int]) = {
  val f = for (name <- names;
               mapVal = namesToValues.getOrElse(name, None)
               if mapVal != None)
    yield {
    mapVal
  }
  f
}

def getValues2(names: Seq[String], namesToValues: Map[String, Int]) = {
  names.flatMap(x => namesToValues.get(x))
}
getValues(Array("Tom", "Fred", "Harry"), Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5))
getValues2(Array("Tom", "Fred", "Harry"), Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5))

def myMkString(collection: Iterable[Any], separator: String): String = {
  collection.reduceLeft((x, y) => x.toString + separator + y.toString).toString
}
myMkString(Array(1, 2, 3), " hello ")

val lst = List(1, 2, 3, 4, 5)
(lst :\ List[Int]())(_ :: _)
(List[Int]() /: lst)(_ :+ _)

lst.foldLeft(List[Int]())((x: List[Int], y: Int) => y +: x)
lst.foldRight(List[Int]())((x: Int, y: List[Int]) => y :+ x)

val prices = List(5.0, 20.0, 9.95)
val quantities = List(10, 2, 1)
(prices zip quantities).map(((x: Double, y: Int) => x * y).tupled)

def twoDimensionalArray(values: Array[Double], numCols: Int): Array[Array[Double]] = {
  values.grouped(numCols).toArray
}
val twoDimenArray = twoDimensionalArray(Array(1, 2, 3, 4, 5, 6), 3)
twoDimenArray.map(x => "[" + x.mkString(", ") + "]")

