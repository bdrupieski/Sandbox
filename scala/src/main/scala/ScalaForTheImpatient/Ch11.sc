class Fraction(private val numerator: Int, private val denominator: Int) {
  def *(other: Fraction): Fraction = {
    new Fraction(this.numerator * other.numerator, this.denominator * other.denominator)
  }
  override def toString: String = {
    s"$numerator/$denominator"
  }
}

val f1 = new Fraction(2, 4)
val f2 = new Fraction(3, 5)
val f3 = f1 * f2

object ApplyAndUpdate {
  def apply(age: Int): Unit = {
    println("apply called with " + age)
  }
  def update(name: String, age: Int): Unit = {
    println("update called with name: " + name + " age: " + age)
  }
}

ApplyAndUpdate(1)
ApplyAndUpdate("Bob") = 2
object Fraction {
  def apply(numerator: Int, denominator: Int): Fraction = {
    new Fraction(numerator, denominator)
  }
  def unapply(input: Fraction): Option[(Int, Int)] = {
    Some((input.numerator, input.denominator))
  }
}

val Fraction(num, den) = Fraction(4, 7) * Fraction(7, 9)

val f4 = Fraction(3, 4) * Fraction(5, 6)
f4 match {
  case Fraction(x, y) => println(s"Fraction $x/$y")
}
object Name {
  def unapply(input: String) = {
    val pos = input.indexOf(" ")
    if (pos == -1) {
      None
    } else {
      Some(input.substring(0, pos), input.substring(pos + 1))
    }
  }
}

val Name(firstName, lastName) = "John Doe"

object Number {
  def unapply(input: String): Option[Int] = {
    try {
      Some(Integer.parseInt(input))
    } catch {
      case ex: NumberFormatException => None
    }
  }
}

val Number(n) = "2"
println(n)

object IsCompound {
  def unapply(input: String): Boolean = input.contains(" ")
}
"John Doe" match {
  case Name(first, last @ IsCompound()) => println("compound name!")
  case Name(first, last) => println(s"$first $last")
}

object Name2 {
  def unapplySeq(input: String): Option[Seq[String]] = {
    Some(input.trim.split("\\s+"))
  }
}
"John Doe Doe Doe Doe" match {
  case Name2(first, last) => println(s"$first $last")
  case Name2(first, middle, last) => println(s"$first $middle $last")
  case Name2(first, middle, last, suffix) => println(s"$first $middle $last $suffix")
  case Name2(first, middle, last, suffix, makeItStop) => println(s"$first $middle $last $suffix $makeItStop")
}