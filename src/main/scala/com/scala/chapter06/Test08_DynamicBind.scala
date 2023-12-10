package com.scala.chapter06

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-06 15:56
 * */
object Test08_DynamicBind {

  def main(args: Array[String]): Unit = {

    val student: Student8 = new Student8
    println(student.name) // student
    student.hello() // hello student

    println("==========================")
    val person: Person8 = new Student8
    println(person.name) //student
    person.hello() //hello student
  }

}


class Person8 {
  val name: String = "person"

  def hello(): Unit = {
    println(s"hello $name")
  }
}

class Student8 extends Person8 {
  override val name: String = "student"

  override def hello(): Unit = {

    println(s"hello $name")
  }
}