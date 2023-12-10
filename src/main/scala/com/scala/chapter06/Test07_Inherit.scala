package com.scala.chapter06

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-06 15:14
 * */
object Test07_Inherit {

  def main(args: Array[String]): Unit = {
    val student1: Student7 = new Student7("marry", 11)
    student1.printInfo()
    /*
    1. 父类的主构造器调用
    2. 父类的辅助构造器调用
    3. 子类的主构造器调用
    Student: marry 11 null
     */
    println("======================================")

    val student2 = new Student7("jack", 12, "scala")
    student2.printInfo()
    /*
    1. 父类的主构造器调用
    2. 父类的辅助构造器调用
    3. 子类的主构造器调用
    4. 子类的辅助构造器调用
    Student: jack 12 scala
     */

    println("======================================")

    val teacher = new Teacher
    teacher.printInfo()
    /*
    1. 父类的主构造器调用
    Teachter
     */

    println("======================================")
    printInfo(student1) //调用了Student类的重写方法
    /*
    Student: marry 11 null
     */
    println("--------------------------------------")
    // Teachter
    printInfo(teacher) //调用了Teacher类的重写方法
    println("--------------------------------------")

    val person = new Person7
    /*
    1. 父类的主构造器调用
    Person : name: null  age : 0
     */
    printInfo(person) //调用了父类的打印方法
  }

  def printInfo(person: Person7): Unit = {
    person.printInfo()
  }
}

// 定义一个父类
class Person7 {
  var name: String = _
  var age: Int = _

  println("1. 父类的主构造器调用")

  def this(name: String, age: Int) {
    this
    println("2. 父类的辅助构造器调用")
    this.name = name
    this.age = age

  }

  def printInfo(): Unit = {
    println(s"Person : name: $name  age : $age")
  }
}

// 定义子类
class Student7(name: String, age: Int) extends Person7(name, age) {

  var stdNo: String = _
  println("3. 子类的主构造器调用")

  def this(name: String, age: Int, stdNo: String) {
    this(name, age)
    println("4. 子类的辅助构造器调用")
    this.stdNo = stdNo
  }

  override def printInfo(): Unit = {

    println(s"Student: $name $age $stdNo")
  }
}

class Teacher extends Person7 {
  override def printInfo(): Unit = {
    println(s"Teacher")
  }
}

