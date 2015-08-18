package ScalaForTheImpatient

object Ch12 {
  def main(args: Array[String]) {
    block
    block2
    untilControl
  }

  def block(): Unit = {
    def runInThread(block: () => Unit) = {
      new Thread {
        override def run() { block() }
      }.start()
    }

    runInThread { () => println("Hello from another thread"); Thread.sleep(1000); println("goodbye") }
  }

  def block2(): Unit = {
    def runInThread(block: => Unit) = {
      new Thread {
        override def run() {
          block
        }
      }.start()
    }

    runInThread {
      println("Hello from another thread")
      Thread.sleep(1000)
      println("goodbye")
    }
  }

  def untilControl(): Unit = {

    def until(condition: => Boolean)(block: => Unit): Unit = {
      if (!condition) {
        block
        until(condition)(block)
      }
    }

    var x = 10
    until (x == 0) {
      x -= 1
      println(x)
    }

  }
}
