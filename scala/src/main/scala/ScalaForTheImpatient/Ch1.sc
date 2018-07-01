val f: Int = 8 * 5 + 2

0.5 * f

val g = "Hello, " + f

g.toUpperCase
g.toLowerCase
g.toCharArray
g.toString

var greeting1: String = null
val greeting2: Any = "hello"
greeting2.getClass.getCanonicalName
greeting2.getClass.getTypeName
greeting2.getClass.getName
greeting2.getClass.getSimpleName

greeting1 = "new string"

1.toString
1.to(10).toString
1.2.to(10.2).by(2.4).toString

"hello".intersect("ol")

1 to 5 toString;
1.to(5).toString()

val big: BigInt = 1234567890L
(big * big * big).toString

math.sqrt(2)
scala.math.sqrt(2)

import math.sqrt
sqrt(5)

import math._
pow(10, 2)
min(E, Pi)

BigInt.probablePrime(32, util.Random)

"hello".distinct
"hello"(3)
"hello" apply 3

"whoa".count(x => x == 'h')
"Whoa".count(x => x.isUpper)
"Whoa".count(_.isUpper)

abs(3 - pow(sqrt(3), 2))

BigInt(2).pow(1024)

import scala.util._
import scala.math.BigInt._

probablePrime(100, Random)

probablePrime(100, Random).toString(36)

"hello".head
"hello".tail
"hello".last

"take".take(3)
"take".take(40)

"drop".drop(3)
"drop".drop(40)

"takeRight".takeRight(3)
"takeRight".takeRight(40)

"dropRight".dropRight(3)
"dropRight".dropRight(40)