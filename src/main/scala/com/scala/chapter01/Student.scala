package com.scala.chapter01

class Student(name: String, var age: Int) {

  def printInfo(): Unit = {
    println(name + " " + age + " " + Student.school)

  }
}

object Student {
  val school: String = "scala"

  def main(args: Array[String]): Unit = {
    val stu = new Student("sanshan", 20)
    stu.printInfo()
  }
}
