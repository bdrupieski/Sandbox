val a = new Array[String](10)
val s = Array("hello", "world")
s(0) = "HELLO"
s

import scala.collection.mutable.ArrayBuffer

val ab1 = ArrayBuffer[Int]()
val ab2 = new ArrayBuffer[Int]

ab1 += 1
ab1 += (1, 2, 3, 4)
ab1 ++= Array(1, 2, 3)
ab1.trimEnd(2)
ab1

ab1.insert(2, 4)
ab1.toArray.toBuffer

for (i <- 0 until ab1.size) yield ab1(i)
for (i <- ab1.indices) yield ab1(i)
for (i <- 0 until ab1.size reverse) yield ab1(i)
for (i <- ab1.size - 1 to 0 by -1) yield ab1(i)
for (i <- ab1.indices.reverse) yield ab1(i)
val f = for (i <- ab1) yield i

f.equals(ab1)
f.eq(ab1)

for (elem <- ab1) yield 2 * elem
for (elem <- ab1 if elem % 2 == 0) yield 2 * elem

ab1.filter(x => x % 2 == 0).map(x => x * 2)
ab1.filter(_ % 2 == 0).map(_ * 2)

ab1.sum
ab1.max
ab1.sortWith((x, y) => x < y)
ab1.sortBy(x => x)

val arr = Array[Int](1, 2, 6, 5, 4)
scala.util.Sorting.quickSort(arr)
arr

ab1.mkString(" . ")
val asStrings = ab1.map(x => x.toString)
String.join(" . ", asStrings: _*)

import collection.JavaConversions.asJavaIterable
String.join(" . ", asStrings)

val matrix = Array.ofDim[Double](2, 3)
matrix(1)(2) = 42
matrix(1)

