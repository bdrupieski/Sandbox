import scala.beans.BeanProperty

class Item(@BeanProperty var price: Double)
val i = new Item(3.0)
i.getPrice
i.setPrice(4)
i.price