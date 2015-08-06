import scala.beans.BeanProperty

class Counter {
  private var value: Int = 0
  def increment() {
    if (value == Int.MaxValue) {
      value = 0
    }
    value += 1
  }
  def current = value
}

class BankAccount(private var _balance: Int) {
  def deposit(amount: Int): Unit = {
    _balance += amount
  }
  def withdraw(amount: Int): Unit = {
    _balance -= amount
  }

  def balance = _balance
}

val b = new BankAccount(0)
b.deposit(30)
b.withdraw(5)
b.balance

class Time(private var _hours: Int, private var _minutes: Int) {

  private val minutesSinceMidnight: Int = _minutes + (_hours * 60)

  def before(otherTime: Time): Boolean = {
    minutesSinceMidnight < otherTime.minutesSinceMidnight
  }

  def hours = _hours
  def minutes = _minutes

  override def toString = s"Time($hours, $minutes)"
}

val time1 = new Time(7, 30)
val time2 = new Time(8, 30)
time1.before(time2)
time2.before(time1)
val time3 = new Time(7, 31)
time1.before(time3)
time3.before(time1)
time1.before(time1)

class Student(@BeanProperty var name: String, @BeanProperty var id: Long)
val s = new Student("Tom", 42L)

class Person(var age: Int, private val fullName: String) {
  if (age < 0) {
    age = 0
  }

  private var splitName = fullName.split(' ')
  val firstName: String = splitName(0)
  val lastName: String = splitName(1)


  override def toString = s"Person($firstName, $lastName, $age)"
}
val p = new Person(-2, "Fred Smith")
p.age

class Car(val manufacturer: String,
          val modelName: String,
          val modelYear: Int = 0,
          val licensePlate: String = "") {

  override def toString = s"Car($manufacturer, $modelName, $modelYear, $licensePlate)"
}

val c1 = new Car("GM", "Volt", 2015, "OUTATIME")
val c2 = new Car("GM", "Volt", 2015)
val c3 = new Car("GM", "Volt")

class Employee(val name: String = "John Q. Public", var salary: Double = 0)
class Employee2() {
  private var _name: String = "John Q. Public"
  private var _salary: Double = 0.0

  def this(name: String, salary: Double) {
    this()
    this._name = name
    this._salary = salary
  }

  def name = _name
  def salary = _salary
}