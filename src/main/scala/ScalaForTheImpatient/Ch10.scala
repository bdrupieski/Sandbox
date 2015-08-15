package ScalaForTheImpatient

object Ch10 {
  def main(args: Array[String]) {
    abstractTrait
    traitWithImplementation
    traitUsedInConstruction
  }

  def abstractTrait() {
    trait Logger {
      def log(msg: String)
    }

    class ConsoleLogger extends Logger with Cloneable with Serializable {
      def log(msg: String) {
        println(msg)
      }
    }

    val c = new ConsoleLogger
    c.log("Hello.")
  }

  def traitWithImplementation() {

    trait ConsoleLogger {
      def log(msg: String) {
        println(msg)
      }
    }

    class Account(var balance: Double)

    class SavingsAccount(initBalance: Double) extends Account(initBalance) with ConsoleLogger {
      def withdraw(amount: Double) {
        if (amount > balance) {
          log("insufficient funds")
        } else {
          balance -= amount
        }
      }
    }

    val s = new SavingsAccount(100.0)
    s.withdraw(50)
    s.withdraw(40)
    s.withdraw(20)

  }

  def traitUsedInConstruction(): Unit = {

    trait Logged {
      def log(msg: String) { }
    }

    class Account
    class SavingsAccount extends Account with Logged {
      def withdraw(amount: Double): Unit = {
        log("withdrawing " + amount)
      }
    }

    trait ConsoleLogger extends Logged {
      override def log(msg: String): Unit = {
        println(msg)
      }
    }

    trait TimestampLogger extends Logged {
      override def log(msg: String): Unit = {
        super.log(new java.util.Date() + " " + msg)
      }
    }

    val s = new SavingsAccount with ConsoleLogger with TimestampLogger
    s.withdraw(20)
  }

  def abstractMethodsInTraits() = {

    trait Logger {
      def log(msg: String)
    }

    trait TimestampLogger extends Logger {
      abstract override def log(msg: String): Unit = {
        super.log(new java.util.Date() + " " + msg)
      }
    }
  }
}
