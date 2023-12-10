package com.scala.chapter06

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-06 14:13
 * */
object Test06_ConstructorParams {

  def main(args: Array[String]): Unit = {

    var student2 = new Student2
    student2.name = "jack"
    student2.age = 20

    //student2: name = jack ,age = 20
    println(s"student2: name = ${student2.name} ,age = ${student2.age}")

    println("================================")

    var student3 = new Student3("bob", 18)
    //student3: name = bob ,age = 18
    println(s"student3: name = ${student3.name} ,age = ${student3.age}")
    println("================================")

    var student4 = new Student4("david", 11)
    // student4: name = david, age = 11
    student4.printinfo()
    // error, 局部变量无法打印对象属性,但如果使用局部变量赋值给属性则没问题
    //println(s"student4: name = ${student4.name}, age = ${student4.age}")

    println("================================")
    val student5 = new Student5("king", 35)
    //student5: name = king ,age = 35
    println(s"student5: name = ${student5.name} ,age = ${student5.age}")

    // 构造器初始化赋值后无法再改变该对象的属性值
    // student5.age = 44  //error

    println("================================")
    val student6 = new Student6("lucy", 10, "scala")
    //student6: name = lucy ,age = 10 , school = scala
    println(s"student6: name = ${student6.name} ,age = ${student6.age} , school = ${student6.school}")
    //student6: name = lucy, age = 10, school = scala
    student6.printInfo()

  }

}

// 定义类
// 无参构造器
class Student2 {
  // 单独定义属性
  var name: String = _
  var age: Int = _
}

// 上面定义等价于
// var 修饰参数，作为类的成员属性使用，可以修改
class Student3(var name: String, var age: Int)

// 主构造器参数无修饰
// 未用任何修饰符修饰，这个参数就是一个局部变量
class Student4(name: String, age: Int) {
  def printinfo(): Unit = {
    println(s"student4: name = $name, age = $age")
  }
}

// 下面这种局部变量的传入可以赋值给属性
/*
class Student4(_name: String, _age: Int) {
  var name: String = _name
  var age: Int = _age
}*/

// val 修饰参数，作为类的只读属性使用，不能修改
class Student5(val name: String, val age: Int)

class Student6(var name: String, var age: Int) {

  var school: String = _

  def this(name: String, age: Int, school: String) {
    this(name, age)
    this.school = school
  }

  def printInfo() {
    println(s"student6: name = ${name}, age = $age, school = $school")
  }

}