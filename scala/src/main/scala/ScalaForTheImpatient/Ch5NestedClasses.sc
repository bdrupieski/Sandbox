import scala.collection.mutable.ArrayBuffer

object Network {
  class Member(val name: String) {
    val contacts = new ArrayBuffer[Member]
  }
}

class Network {

  private val members = new ArrayBuffer[Network.Member]

  def join(name: String): Network.Member = {
    val m = new Network.Member(name)
    members += m
    m
  }
}

val twitter = new Network
val tom = twitter.join("Tom")
val bill = twitter.join("Bill")

tom.contacts += bill
bill.contacts += tom

val facebook = new Network
val sally = facebook.join("Sally")
val joe = facebook.join("Joe")

sally.contacts += tom
sally.contacts += bill


