package com.scala.chapter06

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-06 17:35
 * */
object Test12_Singleton {


  def main(args: Array[String]): Unit = {

    val student1 = Student12.getInstance()
    student1.printInfo() //student: name = alice, age = 20, school = scala

    val student2 = Student12.getInstance()

    println(student1) //com.scala.chapter06.Student12@3ac3fd8b
    println(student2) //com.scala.chapter06.Student12@3ac3fd8b
  }

}

class Student12 private(val name: String, val age: Int) {
  def printInfo() {
    println(s"student: name = ${name}, age = $age, school = ${Student11.school}")
  }
}

/**
 * 饿汉式
 */
/*object Student12 {
  private val student: Student12 = new Student12("alice", 20)

  def getInstance(): Student12 = student
}*/

/**
 * 懒汉式
 */
object Student12 {
  private var student: Student12 = _

  def getInstance(): Student12 = {
    if (student == null) {
      // 如果没有对象实例的话，就创建一个
      student = new Student12("alice", 20)
    }
    student
  }
}
