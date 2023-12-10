package com.scala.chapter06

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-06 17:58
 * */
object Test13_Trait {

  def main(args: Array[String]): Unit = {
    val student: Student13 = new Student13
    student.sayHello()
    student.study()
    student.dating()
    student.play()

    /*
    hello from: student
    hello from : student student
    student student is studying
    student student is dating
    young people student is playing
     */
  }
}

// 定义一个父类
class Person13 {
  val name: String = "person"
  var age: Int = 18

  def sayHello(): Unit = {
    println(s"hello from: $name")
  }

  def increase() = {
    println("person increase")
  }

}

//1. 特质可以同时拥有抽象方法和具体方法
trait Young {

  // 抽象属性
  var age: Int

  // 声明属性
  val name: String = "young"

  // 声明方法
  def play() = {
    println(s"young people $name is playing")

  }

  // 抽象方法
  def dating(): Unit
}

//（2）一个类可以实现/继承多个特质
//（3）所有的 Java 接口都可以当做 Scala 特质使用
class Student13 extends Person13 with Young with java.io.Serializable{
  // 重写冲突的属性,必须重写,否则当前类不清楚使用哪个name
  override val name: String = "student"

  // 实现抽象方法
  override def dating(): Unit = println(s"student $name is dating")

  // 重写父类方法
  override def sayHello(): Unit = {
    super.sayHello()
    println(s"hello from : student $name")
  }

  def study() = println(s"student $name is studying")
}