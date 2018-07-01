def randomArray(n: Int): Array[Int] = {
  (for (x <- 0 until n) yield util.Random.nextInt(n)).toArray
}
val a = randomArray(10)

def swapAdjacent(arr: Array[Int]): Unit = {
  for (x <- 0 until arr.length - 1 by 2) {
    val temp = arr(x)
    arr(x) = arr(x + 1)
    arr(x + 1) = temp
  }
}
val swapArr = Array(1, 2, 3, 4, 5)
swapAdjacent(swapArr)
swapArr

def swapAdjacentYield(arr: Array[Int]): Array[Int] = {
  val a = for (x <- arr.indices by 2;
               y <- 1 to 0 by -1;
               z = x + y if z < arr.length)
  yield arr(z)

  a.toArray
}

val swapArr2 = Array(1, 2, 3, 4, 5)
swapAdjacentYield(swapArr2)

def positiveThenNegative(arr: Array[Int]): Array[Int] = {
  val positives = arr.filter(x => x > 0)
  val zeroes = arr.filter(x => x == 0)
  val negatives = arr.filter(x => x < 0)

  positives ++ zeroes ++ negatives
}
positiveThenNegative(Array(3, -3, 4, 0, -4))

def average(arr: Array[Double]): Double = {
  arr.sum / arr.length
}
average(Array(1, 2, 3, 4))
average(Array(1, 2, 3, 4, 5))
average(Array(1, 2, 3, 4, 5, 6))

Array(1, 2, 5, 6, 8, -2, 7, -9).sortWith(_ > _)

import scala.collection.mutable.ArrayBuffer
ArrayBuffer(1, 2, 5, 6, 8, -2, 7, -9).sortWith(_ > _)

Array(1, 1, 1, 2, 3, 4, 4).distinct

val arryWithNeg = ArrayBuffer(2, 5, -5, -3, 4, -8)

def removeAllNegativeIntegersExceptTheFirst(arr: ArrayBuffer[Int]): Unit = {
  val indexes = for (i <- arr.indices if arr(i) < 0) yield i
  for (j <- indexes.drop(1).reverse) arr.remove(j)
}
removeAllNegativeIntegersExceptTheFirst(arryWithNeg)
arryWithNeg

java.util.TimeZone.getAvailableIDs.filter(x => x.startsWith("America/"))
                                  .map(x => x.replace("America/", ""))
                                  .sortBy(x => x)

import java.awt.datatransfer._
val flavors = SystemFlavorMap.getDefaultFlavorMap.asInstanceOf[SystemFlavorMap]

import scala.collection.mutable
import collection.JavaConversions.asScalaBuffer
val natives: mutable.Buffer[String] = flavors.getNativesForFlavor(DataFlavor.imageFlavor)