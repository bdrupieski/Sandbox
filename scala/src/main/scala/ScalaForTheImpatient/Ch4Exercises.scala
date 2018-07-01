package ScalaForTheImpatient

import scala.io.Source

object Ch4Exercises {
  def main(args: Array[String]) {
    val words = Source.fromFile("words.txt").getLines.toArray

    mutableMap(words)
    immutableMap(words)
    sortedMap(words)
  }

  def mutableMap(words: Array[String]): Unit = {
    val mutableWordMap: collection.mutable.Map[String, Int] = collection.mutable.Map[String, Int]()

    for (word <- words) {
      if (mutableWordMap.contains(word)) {
        mutableWordMap(word) += 1
      } else {
        mutableWordMap(word) = 1
      }
    }

    println(mutableWordMap)
  }

  def immutableMap(words: Array[String]): Unit = {
    val immutableWordMap: collection.immutable.Map[String, Int] = words.groupBy(x => x).map(x => (x._1, x._2.length))
    println(immutableWordMap)
  }

  def sortedMap(words: Array[String]): Unit = {
    val wordMap = words.groupBy(x => x).map(x => (x._1, x._2.length))
    val sortedMap = collection.immutable.SortedMap[String, Int](wordMap.to: _*)
    println(sortedMap)
  }
}
