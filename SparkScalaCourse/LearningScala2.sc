// Flow control

// If / else
if (1 > 3) println("Impossible!") else println("The world make sense.")

if (1 > 3) {
  println("Impossible!")
  println("Really?!")
} else {
  println("ok")
  println("The world makes sense")
}

// Matching
val number = 30
number match {
  case 1 => println("One")
  case 2 => println("Two")
  case 3 => println("Three")
  case _ => println("Something else")
}

for (x <- 1 to 4) {
  val squared = x * x
  println(squared)
}

var x = 10
while (x >= 0) {
  println(x)
  x -= 1
}

x = 0
do { println(x); x+=1 } while (x <= 10)

// Expressions
{val x = 10; x + 20}

println({val x = 10; x + 20})

// EXERCISE
// FIBONACCI (10 FIRST VALUES)

var lastResult = 0; // A
var current = 1;    // B
var newResult = 0; // C
for (n <- 0 to 9) {
  if (n <= 1) {
    println(n)
  }
  else {
    newResult = lastResult + current
    println(newResult)
    lastResult = current
    current = newResult
  }
}