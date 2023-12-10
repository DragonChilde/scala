package com.scala.chapter06

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-06 09:47
 * */
object Test05_Constructor {
  def main(args: Array[String]): Unit = {
    val stu = new Student1
    stu.Student1()
    /*
    1. 主构造方法被调用
    一般方法调用
     */
    println("===================")

    var stu2 = new Student1("marry")
    /*
    1. 主构造方法被调用
    2. 辅助构造方法一被调用
    name: marry age: 0
     */

    println("===================")
    var stu3 = new Student1("lucy", 18)
    /*
    1. 主构造方法被调用
    2. 辅助构造方法一被调用
    name: lucy age: 0
    3. 辅助构造方法二被调用
    name: lucy age: 18
     */
  }
}

// 定义一个类
//（1）如果主构造器无参数，小括号可省略
//class Student1() {
class Student1 {
  //定义属性
  var name: String = _
  var age: Int = _

  println("1. 主构造方法被调用")

  // 声明辅助构造方法
  def this(name: String) {
    // 辅助构造必须调用主构造器,否则无法使用
    this() // 直接调用主构造器,
    println("2. 辅助构造方法一被调用")
    this.name = name
    println(s"name: $name age: $age")
  }

  def this(name: String, age: Int) {

    this(name)
    println("3. 辅助构造方法二被调用")
    this.age = age
    println(s"name: $name age: $age")

  }

  def Student1(): Unit = {
    println("一般方法调用")
  }

}
