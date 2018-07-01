def swap(intPair: (Int, Int)): (Int, Int) = {
  intPair match {
    case (x, y) => (y, x)
  }
}

swap((3, 4))

def swapArray(arr: Array[Int]): Array[Int] = {
  arr match {
    case Array(x, y, remaining @ _*) => Array(y, x) ++ remaining
    case _ => arr
  }
}

swapArray(Array(0, 1, 2, 3))
swapArray(Array(0, 1, 2))
swapArray(Array(0, 1))
swapArray(Array(0))
swapArray(null)

abstract class Item
case class Article(description: String, price: Double) extends Item
case class Bundle(description: String, discount: Double, items: Item*) extends Item
case class Multiple(count: Int, article: Article) extends Item

def price(it: Item): Double = {
  it match {
    case Article(_, p) => p
    case Bundle(_, discount, items @ _*) => items.map(x => price(x)).sum - discount
    case Multiple(count, article) => article.price * count
  }
}

val bundle = Bundle("", 20.0,
  Article("", 40),
  Article("", 20),
  Bundle("", 10,
    Article("", 80),
    Article("", 20)),
  Multiple(5, Article("", 10)))
price(bundle)

val tree = List(List(3, 8), 2, List(5))

def leafSum(tree: List[Any]): Int = {
  tree.map(x => x match {
    case i: Int => i
    case list: List[Any] => leafSum(list)
  }).sum
}
leafSum(tree)

def leafSum1() = {
  sealed abstract class BinaryTree
  case class Leaf(value: Int) extends BinaryTree
  case class Node(left: BinaryTree, right: BinaryTree) extends BinaryTree

  def leafSum(tree: BinaryTree): Int = {
    tree match {
      case l: Leaf => l.value
      case n: Node => leafSum(n.left) + leafSum(n.right)
    }
  }

  val binaryTree = Node(Leaf(1), Node(Node(Leaf(4), Leaf(5)), Leaf(6)))
  leafSum(binaryTree)
}
leafSum1()

def leafSum2() = {
  sealed abstract class BinaryTree
  case class Leaf(value: Int) extends BinaryTree
  case class Node(children: BinaryTree*) extends BinaryTree

  def leafSum(tree: BinaryTree): Int = {
    tree match {
      case l: Leaf => l.value
      case n: Node => n.children.map(x => leafSum(x)).sum
    }
  }

  val binaryTree = Node(Node(Leaf(3), Leaf(8)), Leaf(2), Node(Leaf(5)))
  leafSum(binaryTree)
}
leafSum2()

def eval1() = {
  sealed abstract class BinaryTree
  case class Leaf(value: Int) extends BinaryTree
  case class Node(op: Char, children: BinaryTree*) extends BinaryTree

  def eval(tree: BinaryTree): Int = {
    tree match {
      case Leaf(value) => value
      case Node('*', leaves @ _*) => leaves.map(x => eval(x)).product
      case Node('+', leaves @ _*) => leaves.map(x => eval(x)).sum
      case Node('-', Leaf(value)) => -value
      case Node('-', leaves @ _*) => leaves.map(x => eval(x)).reduceLeft((x, y) => x - y)
    }
  }

  val tree1: BinaryTree = Node('+', Node('*', Leaf(3), Leaf(8)), Leaf(2), Node('-', Leaf(5)))
  println(eval(tree1))

  val tree2: BinaryTree = Node('+', Node('*', Leaf(3), Leaf(8)), Leaf(2), Node('-', Leaf(5), Leaf(1)))
  println(eval(tree2))
}
eval1()

def sumOfOptions(options: List[Option[Int]]): Int = {
  options.map(x => x.getOrElse(0)).sum
}
sumOfOptions(List(Option(1), Option(2), None))

def compose(a: Double => Option[Double], b: Double => Option[Double]): Double => Option[Double] = {
  (x: Double) => b(x) match {
    case Some(x) => a(x)
    case None => None
  }
}

def f(x: Double) = if (x >= 0) Some(math.sqrt(x)) else None
def g(x: Double) = if (x != 1) Some(1 / (x - 1)) else None
val h = compose(f, g)
h(2)
h(1)
h(0)