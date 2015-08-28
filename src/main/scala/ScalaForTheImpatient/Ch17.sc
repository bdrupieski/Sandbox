class MyPair[T <: Comparable[T]](val first: T, val second: T) {
  def smaller: T = if (first.compareTo(second) < 0) first else second
}

new MyPair[Integer](4, 7).smaller
new MyPair[String]("umm", "heya").smaller

val pairConstructor = new MyPair[Integer](_, _)
pairConstructor(1, 2).smaller