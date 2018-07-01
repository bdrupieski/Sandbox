package ScalaForTheImpatient

import scala.annotation.varargs
import scala.io.Source

object Ch15Exercises {

  def main(args: Array[String]) {
    settingVolatileFromMultipleThreads
    factorialWithAssert
  }

  @deprecated
  class VeryDeprecated[@deprecated T](@deprecated param: T) {

    @deprecated
    var field: Int @deprecated = 42

    @deprecated
    def method(@deprecated methodParam: T): Unit = {
      @deprecated
      var someVariable = ""
    }
  }

  @varargs
  def tryVarArgsFromJava(nums: Int*): Int = {
    nums.sum
  }

  def getLines(): String = {
    Source.fromFile("words.txt").getLines().mkString(", ")
  }

  def settingVolatileFromMultipleThreads(): Unit = {

    object ObjectWithAVolatileBoolean {
      @volatile var ohMyGoodness: Boolean = false
    }

    new Thread(new Runnable {
      override def run(): Unit = {
        Thread.sleep(1000)
        ObjectWithAVolatileBoolean.ohMyGoodness = true;
      }
    }).start

    new Thread(new Runnable {
      override def run(): Unit = {
        println("waiting for ohMyGoodness to be true...")
        while (ObjectWithAVolatileBoolean.ohMyGoodness == false) {
          Thread.sleep(200)
          println("still waiting...")
        }
        println("WHOA now it's true!")
      }
    }).start
  }

  def factorialWithAssert(): Unit = {

    def factorial(n: Int): Int = {
      assert(n >= 0)
      (1 to n).product
    }

    println(factorial(5))
  }
}