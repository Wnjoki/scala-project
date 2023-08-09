1 + 1

val x = 42

x * x
//Evaluation Rules
def example = 2      // evaluated when called
val example = 2      // evaluated immediately
lazy val example = 2 // evaluated once when needed

def square(x: Double)    // call by value
//def square(x: => Double) // call by name
//def myFct(bindings: Int*) =  ...  // bindings is a sequence of int, containing a varying # of arguments


//Higher order functions

// sum takes a function that takes an integer and returns an integer then
// returns a function that takes two integers and returns an integer
def sum(f: Int => Int): (Int, Int) => Int 
def sumf(a: Int, b: Int): Int = f(a) + f(b)
  sumf

// same as above. Its type is (Int => Int) => (Int, Int) => Int
def sum(f: Int => Int)(a: Int, b: Int): Int =  ...

// Called like this
sum((x: Int) => x * x * x)          // Anonymous function, i.e. does not have a name
sum(x => x * x * x)                 // Same anonymous function with type inferred

def cube(x: Int) = x * x * x
sum(x => x * x * x)(1, 10) // sum of 1 cubed and 10 cubed
sum(cube)(1, 10)           // same as above 

//Currying-Converting a function with multiple arguments into a function with a single argument that returns another function.
val f2: (Int, Int) => Int = f // uncurried version (type is (Int, Int) => Int)
val f3: Int => Int => Int = f2.curried // transform it to a curried version (type is Int => Int => Int)
val f4: (Int, Int) => Int = Function.uncurried(f3) // go back to the uncurried version (type is (Int, Int) => Int)


//Classes
class MyClass(x: Int, val y: Int,
                      var z: Int):        // Defines a new type MyClass with a constructor
                                          // x will not be available outside MyClass
                                          // val will generate a getter for y
                                          // var will generate a getter and a setter for z
  require(y > 0, "y must be positive")    // precondition, triggering an IllegalArgumentException if not met
  def this (x: Int) =  ...                // auxiliary constructor
  def nb1 = x                             // public method computed every time it is called
  private def test(a: Int): Int =  ...    // private method
  val nb3 = x + y                         // computed only once
  override def toString =                 // overridden method
      x + ", " + y
end MyClass

new MyClass(1, 2, 3) // creates a new object of type

//End markers

//When the body of a class, object, trait, method or value becomes long, visually
//inspecting where it ends might become challenging.
//In these situations, it is possible to explicitly signal to the reader that the body
//is over using the end keyword with the name of the definition:
    class MyClass(a: Int, b: String):
    // body
end MyClass

object MyObject:
    // body
end MyObject

object MyTrait:
    // body
end MyTrait

def myMethod(name: String): Unit =
    println(s"Hello $name")
end myMethod

val myVal: Int =
    42
end myVal

//Operators
(all letters)
|
^
&
< >
= !
:
+ -
* / %
(all other special characters)

//Class hierarchies
abstract class TopLevel:     // abstract class
  def method1(x: Int): Int   // abstract method
  def method2(x: Int): Int =  ...
end TopLevel

class Level1 extends TopLevel:
  def method1(x: Int): Int = ...
  override def method2(x: Int): Int = ... // TopLevel's method2 needs to be explicitly overridden
end Level1

object MyObject extends TopLevel:
  ...  // defines a singleton object. No other instance can be created

//To create an runnable application in Scala:
  @main def run(args: Array[String]) =
  println("Hello world")
  object Hello extends App:
  println("Hello World")

  //Type Parameters
  class MyClass[T](arg1: T):
      ...

    MyClass[Int](1)
    MyClass(1)   // the type is being inferred, i.e. determined based on the value arguments

    //It is possible to restrict the type being used, e.g.
    def myFct[T <: TopLevel](arg: T): T = ... // T must derive from TopLevel or be TopLevel
    def myFct[T >: Level1](arg: T): T = ...   // T must be a supertype of Level1
    def myFct[T >: Level1 <: TopLevel](arg: T): T = ...

    // starting Scala REPL in the sbt type (console)
    //To exit type <ctrl+D>