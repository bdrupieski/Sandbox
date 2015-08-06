object Conversions {
  def inchesToCentimeter(inches: Double): Double = {
    inches * 2.54
  }
  def gallonsToLiter(gallons: Double): Double = {
    gallons * 3.78541
  }
  def milesToKilometer(miles: Double): Double = {
    miles * 1.60934
  }
}

Conversions.milesToKilometer(10)

class UnitConversion(conversionFactor: Double) {
  def convert(units: Double): Double = {
    units * conversionFactor
  }
}

object InchesToCentimeter extends UnitConversion(2.54)
object GallonsToLiter extends UnitConversion(3.78541)
object MilesToKilometer extends UnitConversion(1.60934)

MilesToKilometer.convert(10)

object Origin extends java.awt.Point(0, 0)
Origin.move(1, 1)
Origin.getX

class Point(val x: Double, val y: Double) {
  override def toString = s"Point($x, $y)"
}
object Point {
  def apply(x: Double, y: Double): Point = {
    new Point(x, y)
  }
}

val p1 = Point(1, 3)
val p2 = Point(2, 4)

object Suit extends Enumeration {
  val Clubs = Value("?")
  val Diamonds = Value("?")
  val Hearts = Value("?")
  val Spades = Value("?")

  def isRed(s: Suit.Value): Boolean = {
    s == Diamonds || s == Hearts
  }
}
Suit.Clubs.toString
Suit.isRed(Suit.Clubs)
Suit.isRed(Suit.Diamonds)
Suit.isRed(Suit.Hearts)
Suit.isRed(Suit.Spades)

object Color extends Enumeration {
  val Red = Value(0xff0000)
  val Magenta = Value(0xff00ff)
  val Blue = Value(0x0000ff)
  val Black = Value(0x000000)
  val Yellow = Value(0xffff00)
  val White = Value(0xffffff)
  val Cyan = Value(0x00ffff)
  val Green = Value(0x00ff00)
}

Integer.toHexString(Color.Red.id)