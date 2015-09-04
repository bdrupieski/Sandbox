class Document {
  def setTitle(title: String): this.type = { this }
  def setAuthor(author: String): this.type = { this }
}
class Book extends Document {
  def addChapter(chapter: String): this.type = { this }
}

val book = new Book()
book.setTitle("Calculus").addChapter("Ellis and Gulick")

def passInAStructuralType(target: { def wow(str: String): Any }): Unit = {
  target.wow("hellooo!!!")
}
class Bippity {
  def wow(str: String): Any = {
    println(str)
  }
}
passInAStructuralType(new Bippity)