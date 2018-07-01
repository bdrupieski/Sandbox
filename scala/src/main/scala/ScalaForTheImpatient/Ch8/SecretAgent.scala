package ScalaForTheImpatient.Ch8

class SecretAgent(val codeName: String) extends Person(codeName) {
  override val name: String = "SECRET"
  override def toString = super.toString + "[codeName=" + codeName + "]"
}