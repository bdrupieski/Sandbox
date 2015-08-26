package ScalaForTheImpatient

import scala.annotation.{switch, tailrec}

object Ch14 {
  def main(args: Array[String]) {
    println(sum2(0 to 100000, 0))
    println(noSwitchAnnotation(1))
    println(noSwitchAnnotation2(1))
  }

  @tailrec final def sum2(xs: Seq[Int], partial: BigInt): BigInt = {
    if (xs.isEmpty) partial else sum2(xs.tail, xs.head + partial)
  }

  def noSwitchAnnotation(i: Int): String = {
    i match {
      case 0 => "Zero"
      case 1 => "One"
      case _ => "BOOM"
    }
  }

  def noSwitchAnnotation2(i: Int): String = {
    (i: @switch) match {
      case 0 => "Zero"
      case 1 => "One"
      case _ => "BOOM"
    }
  }
}
