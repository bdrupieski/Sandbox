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