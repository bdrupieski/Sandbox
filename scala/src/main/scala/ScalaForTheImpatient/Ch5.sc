import scala.beans.BeanProperty

class Counter {
  private var value: Int = 0
  def increment() {
    value += 1
  }
  def current = value
}

val someCounter = new Counter
someCounter.increment()
someCounter.increment()
someCounter.increment()
someCounter.current

class Person {
  private var privateAge: Int = 0

  def age = privateAge
  def age_=(newValue: Int) {
    if (newValue > privateAge) {
      privateAge = newValue
    }
  }
}

var fred = new Person
fred.age = 21
fred.age = 30
fred.age = 21

class Message {
  val timeStamp = new java.util.Date
}

class Person2() {
  private var name: String = ""
  private[this] var name2: String = ""

  def comesBefore(otherPerson: Person2) = name < otherPerson.name
  //def comesBefore2(otherPerson: Person2) = name2 < otherPerson.name2
}

class Person3 {
  @BeanProperty var name: String = "test"
}

var p3 = new Person3
p3.getName

class Person4(@BeanProperty var name: String) {
  def this(name: String, repeat: Int) {
    this(name * repeat)
  }
}

var p4 = new Person4("Fred")
p4.getName

var p4a = new Person4("Fred", 3)
p4a.getName

class Person5(var name: String, var age: Int)
val p5 = new Person5("Fred", 3)
p5.name
p5.age