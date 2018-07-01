import scala.annotation.tailrec
import scala.beans.BeanProperty

class Item(@BeanProperty var price: Double)
val i = new Item(3.0)
i.getPrice
i.setPrice(4)
i.price

class Book {
  @throws(classOf[java.io.IOException]) def read(filename: String) = {
    println("reading " + filename)
  }
}

def sum(xs: Seq[Int]): BigInt = {
  if (xs.isEmpty) 0 else xs.head + sum(xs.tail)
}

try {
  sum(0 to 100000)
} catch {
  case e: StackOverflowError => println("overflow!")
}