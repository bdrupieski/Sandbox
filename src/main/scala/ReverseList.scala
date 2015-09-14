object ReverseList {
  def main(args: Array[String]): Unit = {

    val nums = List[Int](1, 2, 3, 4, 5)
    val strings = List[String]("one", "two", "three", "four", "five")

    printReversed(nums)
    printReversed(strings)
  }

  def printReversed[T](xs: List[T]): Unit = {
    println(xs.reverse)
    println(myReverse(xs))
    println(myReverse2(xs))
    println(myReverse3(xs))
  }

  def myReverse[T](xs: List[T]): List[T] = {
    if (xs.tail.isEmpty) {
      List(xs.head)
    } else {
      myReverse(xs.tail) ++ List(xs.head)
    }
  }

  def myReverse2[T](xs: List[T]): List[T] = {
    val s = new scala.collection.mutable.Stack[T]()
    s.pushAll(xs)
    s.toList
  }

  def myReverse3[T](xs: List[T]): List[T] = {
    var l = List[T]()
    for (x <- xs) {
      l = x :: l
    }
    l
  }
}