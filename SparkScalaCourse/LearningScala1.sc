// VALUES are immutable constants.
val hello: String = "Hola!"

// VARIABLES are mutable.
var helloThere: String = hello
helloThere = hello + " There!"
println(helloThere)

val immutableHellowThere = hello + " There"
println((immutableHellowThere))

// Data types

val numberOne: Int = 1
val truth: Boolean = true
val letterA: Char = 'a'
val pi: Double = 3.1459265
val piSinglePrecision: Float = 3.1459265f
val bigNumber: Long = 1234567890
val smallNumber: Byte = 127

println("Here is a mass: " + numberOne + truth + letterA + pi + bigNumber)

println(f"Pi is about $piSinglePrecision%.3f")
println(f"Zero padding on the left: $numberOne%05d")

println(s"I can use the s prefix to use variables like $numberOne $truth and $letterA")

println(s"The s prefix is not limited to variables; I can include any expressions, like ${1+2}")

val theUltimateAnswer: String = "To life, the universe, and everything is 42."
val pattern = """.* ([\d]+).*""".r
val pattern(answerString) = theUltimateAnswer
val answer = answerString.toInt
println(answer)

// Booleans

val isGreater = 1 > 2
val isLesser = 1 < 2
val impossible = isGreater & isLesser // byte wise and
val anotherWay = isGreater && isLesser // logical and; USE THIS

val picard: String = "Picard"
val bestCaptain: String = "Picard"
val isBest: Boolean = picard == bestCaptain // compare strings

// EXERCISE
// Write some code that takes the value of pi, doubles it, and then prints it within
// a string with three decimal places of precision to the right.

print(f"${pi * 2}%.3f")