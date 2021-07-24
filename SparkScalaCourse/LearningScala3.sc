// Functions
// format def <function name>(parameter name: type...): return type = { }

def squareIt(x: Int) : Int = {
  x * x
}

def cubeIt(x: Int) : Int = {
  x * x * x
}

println(squareIt(2))
println(cubeIt(3))

def transformIt(x: Int, f: Int => Int): Int = {
  f(x)
}
val result = transformIt(2, cubeIt)

// lambda function OR anonymous function OR function literal
transformIt(3, x => x * x * x)
transformIt(10, x => x / 2)
transformIt(2, x => {val y = x * 2; y * y})

// EXERCISE
// toUpperCase

def upper(s: String): String = {
  s.toUpperCase()
}
upper("foo")
def transformString(s: String, f: String => String): String = {
  f(s)
}
transformString("foo", f => f.toUpperCase())
transformString("FOO", f => f.toLowerCase())