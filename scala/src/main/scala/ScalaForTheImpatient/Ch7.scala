package com {
  package horstmann {
    package impatient {
      class Person
    }
  }
}

package com {
  package horstmann {
    package impatient {
      class Student extends Person
    }
  }
}

package org {
  package bigjava {
    class Student extends com.horstmann.impatient.Person
  }
}

package com {
  package horstmann {
    object Utils {
      def percentOf(value: Double, rate: Double) = value * rate / 100
    }

    package impatient {
      class Employee(var salary: Double) {
        def giveRaise(rate: Double) {
          salary +=  Utils.percentOf(salary, rate)
        }
      }
    }
  }
}

package com {
  package horstmann {

    package object impatient {
      val defaultPatienceLevel: Int = 4
    }

    package impatient {
      class Manager {
        val subordinates = new _root_.scala.collection.mutable.ArrayBuffer[Employee]
        val managerPatienceLevel: Int = defaultPatienceLevel
      }
    }
  }
}

package com {
  package horstmann {
    package scala {
    }
  }
}

object Ch7 {
  def main(args: Array[String]) {
    val p: com.horstmann.impatient.Person = new com.horstmann.impatient.Person
    println(p)

    val s1: com.horstmann.impatient.Student = new com.horstmann.impatient.Student
    val s2: org.bigjava.Student = new org.bigjava.Student
    println(s1)

    import java.util.{ HashMap => JavaHashMap }
    import scala.collection.mutable.{ HashMap => ScalaHashMap }
    val jHash = new JavaHashMap[Int, Int]
    val scalaHash = new ScalaHashMap[Int, Int]

    import java.lang.System
    println(System.getProperty("user.name"))
  }
}



