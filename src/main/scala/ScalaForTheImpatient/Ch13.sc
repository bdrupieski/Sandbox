import scala.collection.BitSet

val s = Array[Int](1, 2, 3)
val b = BitSet(1, 2, 3)

Array[Int](1, 2, 3) :+ 4
1 +: Array[Int](2, 3, 4)

Set(1, 2, 3) + 4
Set(1, 2, 3) + (4, 5, 6)

Set(1, 2, 3, 4, 5, 6, 7) - 3
Set(1, 2, 3, 4, 5, 6, 7) - 99

Array[Int](1, 2, 3) ++ Array[Int](4, 5, 6)

Set(1, 2)

val arr = (1 to 10).toArray[Int]
arr.head
arr.tail
arr.init
arr.last

val names = List[String]("Peter", "Paul", "Mary")
names.map(x => x.toUpperCase)
names.flatMap(x => x.toUpperCase)
names.map(x => (x.toUpperCase, x.toLowerCase)).flatten(x => x.productIterator.toList)
names.flatMap(x => Vector[String](x.toUpperCase, x.toLowerCase))

"-3+4".collect { case '+' => 1 ; case '-' => -1 }

def fib(a: Int = 0, b: Int = 1): Stream[Int] = Stream.cons(a, fib(b, a+b))

fib().filter(_ % 2 == 0).take(10).force

def fib2(): Stream[Int] = 0 #:: fib2.scanLeft(1)(_ + _)

fib2.filter(_ % 2 == 0).take(10).force