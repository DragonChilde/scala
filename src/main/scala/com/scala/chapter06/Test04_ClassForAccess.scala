package com.scala.chapter06

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-05 09:52
 * */
object Test04_ClassForAccess {

  def main(args: Array[String]): Unit = {
    //val 修饰对象，不能改变对象的引用（即：内存地址），可以改变对象属 性的值
    val person = new Person
    person.sex = "male"

    //person = new Person //error


    println(person.sum(10, 12)) //22


  }
}


class Person {
  private var idCard: String = "12345567"

  protected var name: String = "terry"

  var sex: String = "female"

  private[chapter06] var age: Int = 20

  def printInfo(): Unit = {
    println(s"Person: $idCard $name $sex $age")
  }

  def sum(n1: Int, n2: Int): Int = {
    n1 + n2
  }
}