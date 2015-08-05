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

