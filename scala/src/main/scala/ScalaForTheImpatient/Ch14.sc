val ch: Char = '6'
var digit: Int
val sign: Int = ch match {
  case '+' => 1
  case '-' => -1
  case _ if Character.isDigit(ch) => Character.digit(ch, 10)
  case _ => 0
}

import scala.math._
val x: Double = 123.456
val magicNum: Double = 123.456

x match {
  case Pi => "pi!"
  case E => "e!"
  case `magicNum` => "bingo"
  case _ => "unknown"
}

class Thing
class Person extends Thing
class Dog extends Thing
class Table extends Thing

val a: Thing = new Person

a match {
  case _: Person => "integer!"
  case _: Dog => "double!"
  case _: Table => "hello"
}

val arr = Array[Int](0, 2, 3, 4)
arr match {
  case Array(0) => "0"
  case Array(p, q) => p + " " + q
  case Array(0, _*) => "0... "
  case _ => "umm"
}
for ((k, "") <- sys.props)
  println(k)
abstract class Amount
case class Dollar(value: Double) extends Amount
case class Currency(value: Double, unit: String) extends Amount
case object Nothing extends Amount

val amt: Amount = Dollar(42)
amt match {
  case Dollar(v) => "$" + v
  case Currency(v, u) => "oops"
  case Nothing => ""
}

val currencyAmt: Currency = Currency(scala.math.Pi, "pies")
currencyAmt.copy(value = 22)

val l = List[Int](1, 2, 3)
l match {
  case h :: t => println(h + " " + t)
}

