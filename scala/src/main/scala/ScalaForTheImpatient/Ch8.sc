class Person(val name: String) {
  override def toString = getClass.getName + "[name=" + name + "]"
}
class Employee(name: String, val salary: Double) extends Person(name) {
  override def toString = super.toString + "[salary=" + salary + "]"
}

val p = new Person("Tom")
p
val e = new Employee("Bill", 42)
e

p.isInstanceOf[Person]
p.isInstanceOf[Employee]

e.isInstanceOf[Employee]
e.isInstanceOf[Person]

val eAsPerson = e.asInstanceOf[Person]
eAsPerson.getClass.getName

null.isInstanceOf[Person]
null.asInstanceOf[Person]

p.getClass == classOf[Person]

p match {
  case s: Person => s.name
  case e: Employee => e.name
}

class SecretAgent(val codeName: String) extends Person(codeName) {
  override val name: String = "SECRET"
  override def toString = super.toString + "[codeName=" + codeName + "]"
}

val agent = new SecretAgent("whistler")

val newEmployee = new Employee("John", 20) {
  override def toString = super.toString + "[w00t]"
}

abstract class AbstractPerson() {
  def id(): Int
  val name: String
}

class Employee2(val name: String) extends AbstractPerson {
  def id() = 42
}
val e2 = new Employee2("Joe")
e2.id

val fred = new AbstractPerson {
  override def id(): Int = 88
  override val name: String = "Fred"
}
fred.name

class Creature {
  val range: Int = 10
  val env: Array[Int] = new Array[Int](range)

  override def toString = s"Creature(range:$range, env:(${env.mkString(" ")}))"
}

class Ant extends Creature {
  override val range: Int = 2
}

val c = new Creature
val a = new Ant

class Bug extends { override val range: Int = 3 } with Creature
val b = new Bug

val nullVal: Null = null
val someString: String = nullVal

class Item(val description: String, val price: Double) {

  def canEqual(other: Any): Boolean = other.isInstanceOf[Item]

  override def equals(other: Any): Boolean = other match {
    case that: Item =>
      (that canEqual this) &&
        description == that.description &&
        price == that.price
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(description, price)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}

val item1 = new Item("Banana", 0.49)
val item2 = new Item("Banana", 0.49)
item1 == item2
item1 eq item2
item1 equals item2