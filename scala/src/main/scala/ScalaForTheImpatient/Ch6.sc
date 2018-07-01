object Account {
  private var lastNumber = 0
  private def newUniqueNumber(): Int = {
    lastNumber += 1
    lastNumber
  }
}

class Account {
  val id: Int = Account.newUniqueNumber()
  private var balance = 0.0
  def deposit(amount: Double) {
    balance += amount
  }

  override def toString = s"Account($id, $balance)"
}

val a1 = new Account
val a2 = new Account

abstract class UndoableAction(val description: String) {
  def undo(): Unit
  def redo(): Unit
}

object DoNothingAction extends UndoableAction("Do nothing") {
  override def undo(): Unit = {}
  override def redo(): Unit = {}
}

val actions = Map("open" -> DoNothingAction, "save" -> DoNothingAction)

val arr = new Array[Int](10)
val arr2 = Array[Int](10)

object TrafficLightColor extends Enumeration {
  val Red, Yellow, Green = Value
}
TrafficLightColor.Green
object TrafficLightColor2 extends Enumeration {
  val Red = Value(0, "STOP")
  val Yellow = Value(1)
  val Green = Value(2, "GREEN")
}
TrafficLightColor2.Green

TrafficLightColor(0)
TrafficLightColor2.withName("STOP")