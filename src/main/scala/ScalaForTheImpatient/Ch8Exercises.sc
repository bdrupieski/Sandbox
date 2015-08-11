import scala.collection.mutable.ArrayBuffer

class BankAccount(initialBalance: Double) {
  protected var balance = initialBalance
  def deposit(amount: Double) = { balance += amount; balance }
  def withdraw(amount: Double) = { balance -= amount; balance }
}

class FeeBankAccount(initialBalance: Double) extends BankAccount(initialBalance) {
  override def deposit(amount: Double): Double = {
    super.deposit(amount - 1)
  }
  override def withdraw(amount: Double): Double = {
    super.withdraw(amount + 1)
  }
}

val feeBankAccount: FeeBankAccount = new FeeBankAccount(100)
feeBankAccount.withdraw(50)
feeBankAccount.deposit(20)

class SavingsAccount(initialBalance: Double) extends BankAccount(initialBalance) {
  private var freeTransactionsRemaining = 3

  override def deposit(amount: Double): Double = {
    super.deposit(amount - fee)
    freeTransactionsRemaining -= 1
    balance
  }
  override def withdraw(amount: Double): Double = {
    super.withdraw(amount + fee)
    freeTransactionsRemaining -= 1
    balance
  }

  def earnMonthlyInterest: Unit = {
    balance = balance * 1.01
    freeTransactionsRemaining = 3
  }

  private def fee: Double = if (freeTransactionsRemaining != 0) 0 else 1
}

var savingsAccount: SavingsAccount = new SavingsAccount(100)
savingsAccount.deposit(25)
savingsAccount.deposit(25)
savingsAccount.deposit(25)
savingsAccount.deposit(26)
savingsAccount.earnMonthlyInterest
savingsAccount.withdraw(2)

abstract class Item {
  def price: Double
  def description: String
  override def toString = s"Item($price, $description)"
}

class SimpleItem(val price: Double, val description: String) extends Item
var simpleItem = new SimpleItem(5.95, "can opener")

class Bundle extends Item {
  private var items: ArrayBuffer[Item] = new ArrayBuffer[Item]

  def addItem(item: Item): Unit = {
    items += item
  }

  override def price: Double = {
    items.map(x => x.price).sum
  }
  override def description: String = {
    items.map(x => x.description).mkString(", ")
  }
  override def toString = s"Bundle($price, $description)"
}

val bundle = new Bundle
bundle.addItem(new SimpleItem(1.99, "detergent"))
bundle.addItem(new SimpleItem(0.05, "bubble gum"))
bundle.addItem(new SimpleItem(0.75, "candy bar"))
bundle

class Point(val x: Double, val y: Double) {
  override def toString = s"Point($x, $y)"
}
class LabeledPoint(val label: String, override val x: Double, override val y: Double) extends Point(x, y)
val labeledPoint = new LabeledPoint("Black Thursday", 1939, 230.7)

abstract class Shape {
  def centerPoint: Point
}
class Rectangle(topLeft: Point, bottomRight: Point) extends Shape {
  override def centerPoint: Point = {
    val width = topLeft.x - bottomRight.x
    val height = topLeft.y - bottomRight.y
    new Point(topLeft.x - (width / 2), topLeft.y - (height / 2))
  }
}
class Circle(override val centerPoint: Point) extends Shape

val rect = new Rectangle(new Point(0.0, 0.0), new Point(2.0, 1.0))
rect.centerPoint
val circ = new Circle(new Point(3.5, 3.5))
circ.centerPoint

class Square(x: Int, y: Int, width: Int) extends java.awt.Rectangle(x, y, width, width) {
  def this(width: Int) {
    this(0, 0, width)
  }
  def this() {
    this(0, 0, 0)
  }
}

val square1 = new Square(1, 2, 3)
val square2 = new Square(4)
val square3 = new Square()