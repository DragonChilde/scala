package com.scala.chapter06

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-06 16:08
 * */
object Test09_AbstractClass {

  def main(args: Array[String]): Unit = {

    val student = new Student9

    /*
    person eat
    student eat
     */
    student.eat()
    student.sleep() //student sleep
  }

}


// 定义抽象类
abstract class Person9 {
  // 非抽象属性
  var name: String = "person"

  // 抽象属性
  var age: Int

  // 非抽象方法
  def eat(): Unit = {
    println("person eat")
  }

  // 抽象方法
  def sleep(): Unit
}

// 定义具体的实现子类
class Student9 extends Person9 {
  // 实现抽象属性和方法
  var age: Int = 11

  override def sleep(): Unit = {
    println("student sleep")
  }


  // 父类已定义是可变变量,子类重写无法改变其类型
  //override val name: String = "student"
  // 重写非抽象属性和方法
  name = "student"

  override def eat(): Unit = {
    super.eat()
    println(s"$name eat")
  }
}