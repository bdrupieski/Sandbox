package ScalaForTheImpatient

object Ch11Exercises {
  def main(args: Array[String]) {
    fractionOverloading
    moneyOverloading
  }

  def fractionOverloading(): Unit = {

    object Fraction {
      def apply(n: Int, d: Int): Fraction = {
        new Fraction(n, d)
      }
    }

    class Fraction(n: Int, d: Int) {

      private val num: Int = if (d == 0) 1 else n * sign(d) / gcd(n, d);
      private val den: Int = if (d == 0) 1 else d * sign(d) / gcd(n, d);
      override def toString: String = num + "/" + den

      def sign(a: Int): Int = if (a > 0) 1 else if (a < 0) -1 else 0
      def gcd(a: Int, b: Int): Int = if (b == 0) math.abs(a) else gcd(b, a % b)

      def +(other: Fraction): Fraction = {
        Fraction((this.num * other.den) + (this.den * other.num), this.den * other.den)
      }

      def -(other: Fraction): Fraction = {
        Fraction((this.num * other.den) - (this.den * other.num), this.den * other.den)
      }

      def *(other: Fraction): Fraction = {
        Fraction(this.num * other.num, this.den * other.den)
      }

      def /(other: Fraction): Fraction = {
        Fraction(this.num * other.den, this.den * other.num)
      }
    }

    println(Fraction(1, 2) / Fraction(1, 4))
    println(Fraction(1, 2) * Fraction(1, 4))
    println(Fraction(1, 2) + Fraction(1, 4))
    println(Fraction(1, 2) - Fraction(1, 4))
  }

  def moneyOverloading(): Unit = {

    object Money {
      def apply(dollars: Int, cents: Int): Money = {
        new Money(dollars, cents)
      }
    }

    class Money(dollars: Int, cents: Int) {
      val c: Int = cents % 100
      val d: Int = dollars + (cents / 100)

      def +(other: Money): Money = {
        Money(this.d + other.d, this.c + other.c)
      }

      override def toString: String = s"$d.$c"
    }

    println(Money(1, 75) + Money(0, 50))
  }
}
