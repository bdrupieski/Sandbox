package ScalaForTheImpatient

import scala.util.Random

object Ch10Exercises {
  def main(args: Array[String]) {
    rectangleLikeTrait
    orderedPointTrait
    cryptoLoggerTrait
    bufferedTrait
  }

  def rectangleLikeTrait(): Unit = {

    trait RectangleLike {
      def getX() : Double
      def getY() : Double
      def getWidth() : Double
      def getHeight() : Double
      def setFrame(x: Double, y: Double, width: Double, height: Double)
      def translate(dx: Double, dy: Double): Unit = {
        setFrame(getX() + dx, getY() + dy, getWidth(), getHeight())
      }
      def grow(h: Double, w: Double): Unit = {
        setFrame(getX(), getY(), getWidth() + w, getHeight() + h)
      }
      override def toString: String = {
        "[%f %f %f %f]".format(getX(), getY(), getWidth(), getHeight())
      }
    }

    val r = new java.awt.geom.Ellipse2D.Double(5, 10, 20, 30) with RectangleLike
    println(r)
    r.translate(10, -10)
    println(r)
    r.grow(10, 20)
    println(r)
  }

  def orderedPointTrait(): Unit = {

    class OrderedPoint(x: Int, y: Int) extends java.awt.Point(x, y) with scala.math.Ordered[java.awt.Point] {
      override def compare(that: java.awt.Point): Int = {
        if (this.x != that.x) {
          this.x.compareTo(that.x)
        } else {
          this.y.compareTo(that.y)
        }
      }
      override def toString: String = {
        "[x:%d y:%d]".format(x, y)
      }
    }

    val points = for (x <- 1 to 10) yield { new OrderedPoint(Random.nextInt(10), Random.nextInt(10)) }
    println(points)
    val sortedPoints = points.sortWith((x, y) => x.compare(y) < 0)
    println(sortedPoints)
  }

  def cryptoLoggerTrait(): Unit = {

    trait Logger {
      def log(msg: String)
    }

    trait ConsoleLogger extends Logger {
      override def log(msg: String): Unit = {
        println(msg)
      }
    }

    trait CryptoLogger extends Logger {
      val cryptoLoggerKey: Int = 3

      abstract override def log(msg: String): Unit = {
        val encodedMsg = msg.map(c => (c + cryptoLoggerKey).toChar).mkString("")
        super.log(encodedMsg)
      }
    }

    object LoggingTest extends ConsoleLogger with CryptoLogger {
      def logIt(msg: String): Unit = {
        log(msg)
      }
    }

    LoggingTest.logIt("Hello")
    LoggingTest.logIt("abcdefghijklmnopqrstuvwxyz") // not a real caesar cipher

  }

  def bufferedTrait(): Unit = {

    trait Buffered {
      this: java.io.InputStream =>

      val buffer = new java.io.BufferedInputStream(this)

      override def read(b: Array[Byte]): Int = {
        buffer.read(b)
      }
    }

    val f = new java.io.FileInputStream("words.txt") with Buffered

    val bytes: Array[Byte] = new Array[Byte](1024)
    f.read(bytes)
    bytes.foreach(b => print(b.toChar))
  }
}