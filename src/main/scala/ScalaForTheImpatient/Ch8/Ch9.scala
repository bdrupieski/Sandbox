package ScalaForTheImpatient.Ch8

import java.io._

import scala.io.{Codec, Source}

object Ch9 {
  def main(args: Array[String]) = {
    readAllAtOnce
    readCharacters
    readBuffered
    regexTokens
    readUrl
    fromString
    readBinary
    writeToFile
    serialization
    regex
  }

  def readAllAtOnce = {
    val source = Source.fromFile("words.txt")(Codec.UTF8)
    println(source.getLines.mkString(", "))
  }

  def readCharacters = {
    val source = Source.fromFile("words.txt")(Codec.UTF8)

    for (c <- source) {
      print(c)
    }
  }

  def readBuffered = {
    val source = Source.fromFile("words.txt")
    val iter = source.buffered
    while (iter.hasNext) {
      if (iter.head == 'a') {
        print(iter.next)
      } else {
        iter.next
      }
    }
    println; println
  }

  def regexTokens = {
    val source = Source.fromFile("words.txt")
    val tokens = source.mkString.split("\\s+")
    println(tokens.mkString(", "))
  }

  def readUrl = {
    val source = Source.fromURL("http://www.google.com")
    println(source.mkString)
  }

  def fromString = {
    val source = Source.fromString("Hello, world")
    println(source.getLines().mkString)
  }

  def readBinary = {
    val file = new File("words.txt")
    val in = new FileInputStream(file)
    val bytes = new Array[Byte](file.length.toInt)
    in.read(bytes)
    in.close
    println(bytes.mkString(" "))
  }

  def writeToFile = {
    val out = new PrintWriter("out.txt")
    for (i <- 1 to 100) {
      out.println(i)
    }
    out.close
  }

  def serialization = {
    @SerialVersionUID(42L) class Person(val name: String) extends Serializable
    val fred = new Person("Fred")
    val out = new ObjectOutputStream(new FileOutputStream("test.obj"))
    out.writeObject(fred)
    out.close()
    val in = new ObjectInputStream(new FileInputStream("test.obj"))
    val savedFred = in.readObject().asInstanceOf[Person]
    println("Saved value: " + savedFred.name)
  }

  def regex = {
    val numPattern = "[0-9]+".r

    for (matchedString <- numPattern.findAllIn("99 bottles of beer, 98 bottles of beer")) {
      println(matchedString)
    }

    println(numPattern.replaceAllIn("number: 42, number: 41123", "REDACTED"))
  }
}


