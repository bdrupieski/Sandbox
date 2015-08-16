package ScalaForTheImpatient

import sun.font.TrueTypeFont

object Ch11Exercises {
  def main(args: Array[String]) {
    fractionOverloading
    moneyOverloading
    constructHtmlTable
    addAsciiArt
    implementBitSequence
    richFileUnapply
    richFileUnapplySeq
  }

  def fractionOverloading(): Unit = {

    object Fraction {
      def apply(n: Int, d: Int): Fraction = {
        new Fraction(n, d)
      }
    }

    class Fraction(n: Int, d: Int) {

      private val num: Int = if (d == 0) 1 else n * sign(d) / gcd(n, d)
      private val den: Int = if (d == 0) 1 else d * sign(d) / gcd(n, d)
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

  def constructHtmlTable(): Unit = {

    import collection.mutable.ArrayBuffer

    class Table {

      val rows = new ArrayBuffer[ArrayBuffer[String]]
      rows += new ArrayBuffer[String]

      def |(data: String): Table = {
        rows.last += data
        this
      }

      def ||(data: String): Table = {
        rows += new ArrayBuffer[String]
        rows.last += data
        this
      }

      override def toString: String = {
        val sb = new StringBuilder
        sb.append("<table>")
        for (row <- rows) {
          sb.append("<tr>")
          for (col <- row) {
            sb.append("<td>")
            sb.append(col)
            sb.append("</td>")
          }
          sb.append("</tr>")
        }
        sb.append("</table>")
        sb.toString()
      }
    }

    object Table {
      def apply(): Table = {
        new Table()
      }
    }

    val t = Table() | "Java" | "Scala" || "Gosling" | "Odersky" || "JVM" | "JVM, .NET"
    println(t.toString())
  }

  def addAsciiArt(): Unit = {

    object AsciiArt {
      def apply(art: String): AsciiArt = {
        new AsciiArt(art)
      }
    }

    class AsciiArt(val art: String) {
      def +(other: AsciiArt): AsciiArt = {
        val thisLines = this.art.split(sys.props("line.separator"))
        val otherLines = other.art.split(sys.props("line.separator"))

        val newArt = thisLines.zip(otherLines).map(x => x._1 + x._2).mkString(sys.props("line.separator"))
        AsciiArt(newArt)
      }

      override def toString: String = {
        art
      }
    }

    val a = new AsciiArt(""" /\_/\
                           #( ' ' )
                           #(  -  )
                           # | | |
                           #(__|__)""".stripMargin('#'))

    val b = new AsciiArt("""   -----
                           # / Hello \
                           #<  Scala |
                           # \ Coder /
                           #   -----""".stripMargin('#'))

    println(a)
    println(b)
    println(a + b)
  }

  def implementBitSequence(): Unit = {
    class BitSequence {
      var value: Long = 0L

      def apply(index: Int): Long = {
        if ((value & (1L << index)) != 0) 1 else 0
      }

      def update(index: Int, newValue: Boolean): Unit = {
        if (newValue) {
          value |= (1 << index)
        } else {
          value ^= (1 << index)
        }
      }

      override def toString: String = {
        (63 to 0 by -1).map((x: Int) => apply(x)).mkString("")
      }
    }

    val b = new BitSequence
    b(1) = true
    b(4) = true
    b(5) = true
    println(b)
    b(5) = false
    println(b)
  }

  def richFileUnapply(): Unit = {

    object RichFile {
      def unapply(fullPath: String): Option[(String, String, String)] = {
        val lastSlash = fullPath.lastIndexOf("/")
        if (lastSlash == -1) {
          None
        } else {
          val path = fullPath.substring(0, lastSlash)
          val file = fullPath.substring(lastSlash + 1)

          val extensionIndex = file.indexOf(".")

          if (extensionIndex == -1) {
            None
          } else {
            val filename = file.substring(0, extensionIndex)
            val extension = file.substring(extensionIndex + 1)

            Some((path, filename, extension))
          }
        }
      }
    }

    val RichFile(path, filename, extension) = "/home/cay/hello.txt"
    println(s"$path $filename $extension")
  }

  def richFileUnapplySeq(): Unit = {

    object RichFile {
      def unapplySeq(fullPath: String): Option[Seq[String]] = {
        Some(fullPath.split("/"))
      }
    }

    val RichFile(a, b, c) = "home/cay/hello.txt"
    println(s"$a $b $c")
  }
}
