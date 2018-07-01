package ScalaForTheImpatient

import java.io.PrintWriter
import java.nio.file.Paths

import scala.io.Source

object Ch9Exercises {
  def main(args: Array[String]) {
    reverseLinesInFile
    printWordsGreaterThanNCharacters(4)
    numbersFromFile
    powersOfTwo
  }

  def reverseLinesInFile = {
    val fileToReverse: String = "out.txt"
    val outputFile: String = "outReversed.txt"
    if (java.nio.file.Files.exists(Paths.get(fileToReverse))) {

      val reversedLines = Source.fromFile(fileToReverse).getLines.toArray.reverse

      val out = new PrintWriter(outputFile)
      reversedLines.foreach(x => out.println(x));
      out.close
    }
  }

  def printWordsGreaterThanNCharacters(n: Int) = {
    Source.fromFile("words.txt").getLines().filter(x => x.length > n).foreach(x => println(x))
  }

  def numbersFromFile = {
    val nums = Source.fromFile("numbers.txt").getLines().map(_.toDouble).toArray

    println("Sum: " + nums.sum)
    println("Average: " + (nums.sum / nums.length))
    println("Maximum: " + nums.max)
    println("Minimum: " + nums.min)
  }

  def powersOfTwo = {
    val powersOf2 =
      for (i <- 1 to 20;
           power = i << 2;
           inverse = (1.0 / i)) yield {
        (power, inverse)
      }

    val out = new PrintWriter("powersOf2.txt")
    powersOf2.foreach(x => out.println("%20d %20f".format(x._1, x._2)))
    out.close
  }
}
