// Data Structures

// Tuples
// Immutable lists
val captainStuff = ("a", "b", "c")

// Refer to one field based in ONE-BASED index (starts with 1)
println(captainStuff._1)
println(captainStuff._2)
println(captainStuff._3)

val myMap = ("a" -> "joao", "b" -> "maria")
println(myMap._2)

val mixedMap = ("a", 1122112, false)
println(mixedMap._1)

// Lists
// Like a tuple, but more functionality
// MUST be of same type
// Refer to one field based in ZERO-BASED index (starts with 0)
var shipList = List("apple", "banana", "grapefruit" )
println(shipList(0))

println(shipList.head) // first
println(shipList.tail) // all except first one

for (ship <- shipList) { println(ship) }

val reversed = shipList.map((ship: String) => {ship.reverse })

val numberlist = List(1,2,3,4,5)
val sum = numberlist.reduce( ( total: Int, current: Int) => total + current)

// filter
val iHateFives = numberlist.filter((x: Int) => x != 5)
var iHateThrees = numberlist.filter(_ != 3)

// concatenate
val moreNumbers = List(6, 7, 8)
var lotsOfNumbers = numberlist ++ moreNumbers

val sorted = reversed.sorted
val lotsOfDuplicates = numberlist ++ numberlist
val distinctList = lotsOfDuplicates.distinct
val maxValue = distinctList.max
val total = distinctList.sum
val hastThree = iHateThrees.contains(3)

// Maps
val myMap = Map("a" -> "letter a", "b" -> "letter b")
println(myMap("b"))
myMap.contains("c")
val result = util.Try(myMap("c")) getOrElse "Unable to find"

















