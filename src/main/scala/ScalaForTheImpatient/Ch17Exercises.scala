package ScalaForTheImpatient

object Ch17Exercises {
  def main(args: Array[String]) {
    immutableSwap
    mutableSwap
    genericMethodSwap
    genericMiddle
    genericSwapIfTypesAreTheSame
  }

  def immutableSwap(): Unit = {

    class Pair[T, S](val first: T, val second: S) {
      def swap: Pair[S, T] = {
        new Pair(second, first)
      }
      override def toString = s"$first $second"
    }

    val p = new Pair("hi", 1)
    println(p + " " + p.swap)
  }

  def mutableSwap(): Unit = {

    class Pair[T](var first: T, var second: T) {
      def swap: Unit = {
        val temp: T = first
        first = second
        second = temp
      }
      override def toString = s"$first $second"
    }
    val p = new Pair("hi", 1)
    println(p)
    p.swap
    println(p)
  }

  def genericMethodSwap(): Unit = {

    class Pair[T, S](val first: T, val second: S) {
      override def toString = s"$first $second"
    }

    def swap[T, S](pair: Pair[T, S]): Pair[S, T] = {
      new Pair(pair.second, pair.first)
    }

    val p = new Pair("hi", 1)
    val pSwapped = swap(p)
    println(p)
    println(pSwapped)
  }

  def genericMiddle(): Unit = {

    def middle[T : Manifest](it: Iterable[T]): T = {
      val middle = it.size / 2
      val arr = it.toArray
      arr(middle)
    }

    println(middle(Array(1, 2, 3, 4, 5)))
    println(middle(Array('a', 'b', 'c', 'd')))
  }

  def genericSwapIfTypesAreTheSame(): Unit = {

    class Pair[S, T](var first: S, var second: T) {
      def swap(implicit ev: S =:= T, ev2: T =:= S): Unit = {
        val temp: T = first
        first = second
        second = temp
      }
      override def toString = s"$first $second"
    }

    val p = new Pair("hi", "hello")
    println(p)
    p.swap
    println(p)

    val p2 = new Pair(22, 44)
    println(p2)
    p2.swap
    println(p2)
  }
}
