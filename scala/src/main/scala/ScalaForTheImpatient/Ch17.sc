import scala.runtime.RichInt

class MyPair[T <: Comparable[T]](val first: T, val second: T) {
  def smaller: T = if (first.compareTo(second) < 0) first else second
}

new MyPair[Integer](4, 7).smaller
new MyPair[String]("umm", "heya").smaller

val pairConstructor = new MyPair[Integer](_, _)
pairConstructor(1, 2).smaller

class Person extends Comparable[Person] {
  override def compareTo(o: Person): Int = if (this.equals(o)) 1 else -1
}
class Student extends Person with Comparable[Person] {
  override def compareTo(o: Person): Int = super.compareTo(o)
}

new MyPair[Person](new Person(), new Person()).smaller
//new MyPair[Student](new Student(), new Student()).smaller

class PairWithReplace[T](val first: T, val second: T) {
  def replaceFirst[R >: T](newFirst: R) = new PairWithReplace[R](newFirst, second)
}

val p = new PairWithReplace[Student](new Student(), new Student())
p.replaceFirst(new Student())
p.replaceFirst(new Person())

class PairWithViewBound[T <% Comparable[T]](val first: T, val second: T) {
  def smaller: T = if (first.compareTo(second) < 0) first else second
}
new PairWithViewBound(1, 2).smaller

val d: RichInt = Predef.intWrapper(3)
d.compare(4)

class PairWithOrderedViewBound[T <% Ordered[T]](val first: T, val second: T) {
  def smaller: T = if (first.compareTo(second) < 0) first else second
}
new PairWithOrderedViewBound[Int](1, 2).smaller

class PairWithContextBound[T : Ordering](val first: T, val second: T) {
  def smaller(implicit ord: Ordering[T]) = {
    if (ord.compare(first, second) < 0) first else second
  }
}
new PairWithContextBound[Int](1, 2).smaller

def makeArray[T : Manifest](first: T, second: T) = {
  Array(first, second)
}
makeArray(1, 2).getClass.getCanonicalName

class PairWithTypeConstraint[T](val first: T, val second: T) {
  def smaller(implicit ev: T <:< Ordered[T]) = {
    if (first < second) first else second
  }
}
new PairWithTypeConstraint[Integer](1, 2)

class CovariantPair[+T](val first: T, val second: T)

def makeFriends(p: CovariantPair[Person]) = { p.first }
makeFriends(new CovariantPair[Person](new Person(), new Person()))
makeFriends(new CovariantPair[Student](new Student(), new Student()))