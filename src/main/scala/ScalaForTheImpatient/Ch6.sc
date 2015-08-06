object Account {
  private var lastNumber = 0
  private def newUniqueNumber(): Int = {
    lastNumber += 1;
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