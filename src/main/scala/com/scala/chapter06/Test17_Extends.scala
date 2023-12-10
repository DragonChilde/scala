package com.scala.chapter06

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-08 10:14
 * */
object Test17_Extends {

  def main(args: Array[String]): Unit = {
    // 1. 类型的检测和转换
    val student: Student17 = new Student17("alice", 18)
    student.study() //student study
    student.sayHi() //hi from student alice

    val person: Person17 = new Student17("jack", 20)
    person.sayHi() //hi from student jack

    println("======================")
    // 类型判断
    println("student is Student17 : " + student.isInstanceOf[Student17]) //student is Student17 : true
    println("student is Person17 : " + student.isInstanceOf[Person17]) //student is Person17 : true
    println("person is Person17 : " + person.isInstanceOf[Person17]) //person is Person17 : true
    println("person is Student17 : " + person.isInstanceOf[Student17]) //person is Student17 : true
    println("======================")

    val person2: Person17 = new Person17("carry", 11)
    println("person2 is Student17: " + person2.isInstanceOf[Student17]) //person2 is Student17: false
    println("======================")

    // 类型转换
    if (person.isInstanceOf[Student17]) {
      val newStudent = person.asInstanceOf[Student17]
      newStudent.study() //student study
    }
    println("======================")
    println(classOf[Student17]) //class com.scala.chapter06.Student17
    println("======================")
    // 2. 测试枚举类
    println(WorkDay.MONDAY) //Monday
  }

}


class Person17(val name: String, val age: Int) {
  def sayHi(): Unit = {
    println("hi from person" + name)
  }
}


class Student17(name: String, age: Int) extends Person17(name, age) {
  override def sayHi(): Unit = {
    println("hi from student " + name)
  }

  def study(): Unit = {
    println("student study")
  }
}

object WorkDay extends Enumeration {
  val MONDAY = Value(1, "Monday")
  val TUESDAY = Value(2, "TuesDay")
}

object TestApp extends App {
  println("app start") //app start

  type MyString = String
  val a: MyString = "abc"
  println(a) //abc
}