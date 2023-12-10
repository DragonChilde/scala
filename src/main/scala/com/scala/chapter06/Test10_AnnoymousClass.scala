package com.scala.chapter06

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-06 16:36
 * */
object Test10_AnnoymousClass {

  def main(args: Array[String]): Unit = {

    val person: Person10 = new Person10 {
      override var name: String = "jack"

      override def eat(): Unit = println(s"$name eat")
    }

    println(person.name)  //jack
    person.eat()  //jack eat
  }

}

// 定义抽象类
abstract class Person10 {
  var name: String

  def eat(): Unit
}
