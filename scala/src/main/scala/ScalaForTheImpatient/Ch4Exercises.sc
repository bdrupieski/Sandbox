import scala.collection.immutable.ListMap

val gizmos = Map("iPod" -> 100, "iPad" -> 150, "iMac" -> 300)
val discountedGismos = for ((k, v) <- gizmos) yield (k, v * 0.9)

val orderedDayMap = ListMap(
  "Monday" -> java.util.Calendar.MONDAY,
  "Tuesday" -> java.util.Calendar.TUESDAY,
  "Wednesday" -> java.util.Calendar.WEDNESDAY,
  "Thursday" -> java.util.Calendar.THURSDAY,
  "Friday" -> java.util.Calendar.FRIDAY,
  "Saturday" -> java.util.Calendar.SATURDAY,
  "Sunday" -> java.util.Calendar.SUNDAY)

import collection.JavaConversions.propertiesAsScalaMap
val sysProps: collection.Map[String, String] = System.getProperties

val maxKeyLength = sysProps.keys.maxBy(x => x.length).length
for ((k, v) <- sysProps) {
  println(String.format("%1$-" + maxKeyLength + "s | %s", k, v))
}

def minmax(values: Array[Int]): (Int, Int) = {
  (values.min, values.max)
}
minmax(Array(1, 2, 3, 4, 5, 6))

def lteqgt(values: Array[Int], v: Int): (Int, Int, Int) = {
  (values.count(x => x < v), values.count(x => x == v), values.count(x => x > v))
}
lteqgt(Array(1, 2, 3, 4, 5, 6), 4)

"Hello" zip "World"