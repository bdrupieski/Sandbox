package ScalaForTheImpatient.Ch8

class Person(val name: String) {
  override def toString = getClass.getName + "[name=" + name + "]"
}