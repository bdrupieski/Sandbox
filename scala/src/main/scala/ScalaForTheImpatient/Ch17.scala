package ScalaForTheImpatient

object Ch17 {
  def main(args: Array[String]) {
    contravariance
  }

  def contravariance(): Unit = {

    trait Friend[-T] {
      def befriend(someone: T)
    }

    class Person(val name: String) extends Friend[Person] {
      override def befriend(someone: Person): Unit = { println(this.name + " befriended " + someone) }
      override def toString = s"$name"
    }

    class Student(name: String) extends Person(name)

    def makeFriendsWith(s: Student, f: Friend[Student]): Unit = {
      f.befriend(s)
    }

    val fred = new Person("fred")
    val susan = new Student("susan")

    makeFriendsWith(susan, fred)

  }
}
