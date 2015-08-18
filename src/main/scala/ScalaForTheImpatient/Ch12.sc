val num = 3.14
val fun: Double => Double = math.ceil _
fun(num)
Array[Double](3.14, 1.42, 2.0).map(fun)

val triple: Double => Double = (x: Double) => 3 * x
Array(3.14, 1.42, 2.0).map{ (x: Double) => x * 3 }
Array(3.14, 1.42, 2.0) map { (x: Double) => x * 3 }

def valueAtOneQuarter(f: Double => Double): Double = {
  f(0.25)
}
valueAtOneQuarter(math.ceil _)
valueAtOneQuarter(math.sqrt _)

def mulBy(factor: Double): (Double => Double) = {
  (x: Double) => x * factor
}
val m = mulBy(3)
m(4)
mulBy(2)(42)

import java.awt.event.{ActionListener, ActionEvent}
import javax.swing.JButton

implicit def makeAction(action: (ActionEvent) => Unit): ActionListener = {
  new ActionListener {
    override def actionPerformed(action: ActionEvent): Unit = {
      println(action.getActionCommand)
    }
  }
}

val button = new JButton("a button")
button.addActionListener((event: ActionEvent) => println("whoaaa!!"))

def mulOneAtATime(x: Int): (Int => Int) = (y: Int) => x * y
mulOneAtATime(3)(4)
def mulOneAtATime2(x: Int)(y: Int): Int = x * y
mulOneAtATime2(3)(4)