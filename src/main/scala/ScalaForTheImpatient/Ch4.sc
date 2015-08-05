
import scala.collection.mutable

val scores = Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)
val scoresMutable = mutable.Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)
val scoresHashMap = mutable.HashMap[String, Int]("Alice" -> 10)

scores("Bob")
scoresMutable("Brian") = 3

scoresMutable.get("DoesntExist")
scoresMutable.getOrElse("DoesntExist", -1)

for ((k, v) <- scores) yield (k, v + 3)
scores.map(x => (x._1, x._2 + 3))

val scoresImmutableSortedMap = collection.immutable.SortedMap("Bob" -> 3, "Alice" -> 10)
scoresImmutableSortedMap

import collection.JavaConversions.mapAsScalaMap
val scoresTreeMap: mutable.Map[String, Int] = new java.util.TreeMap[String, Int]
scoresTreeMap("Bob") = 3
scoresTreeMap("Alice") = 10
scoresTreeMap

val t: Tuple3[Int, Double, String] = (1, 3.14, "Fred")
val t2: (Int, Double, String) = (1, 3.14, "Fred")
t._1

val (first, second, third) = t
first
second
third

"New York".partition(x => x.isUpper)

val symbols = Array("<", "-", ">")
val counts = Array(2, 10, 2)
val pairs = symbols.zip(counts)
for ((s, n) <- pairs) print (s * n)
