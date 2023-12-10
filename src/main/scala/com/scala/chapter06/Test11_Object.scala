package com.scala.chapter06

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-06 16:38
 * */
object Test11_Object {

  def main(args: Array[String]): Unit = {

    //val student1 = new Student11("alice", 18)
    //student1.printInfo() //student: name = alice, age = 18, school = scala

    println("========================")
    // 3. 伴生对象中的属性和方法都可以通过伴生对象名（类名）直接调用访问。
    var student2 = Student11.newStudent("jack", 20)
    student2.printInfo() //student: name = jack, age = 20, school = scala

    println("========================")


    var student3 = Student11.apply("lucy", 33)
    student3.printInfo() //student: name = lucy, age = 33, school = scala

    println("========================")

    // 1. 通过伴生对象的 apply 方法，实现不使用 new 关键字创建对象
    var student4 = Student11("david", 44)
    student4.printInfo() //student: name = david, age = 44, school = scala

  }

}


// 2. 伴生对象对应的类称之为伴生类，伴生对象的名称应该和伴生类名一致。
// 如果想让主构造器变成私有的，可以在()之前加上 private ,当为私有后,只有伴生对象才可进行调用
class Student11 private(val name: String, val age: Int) {

  def printInfo() {
    println(s"student: name = ${name}, age = $age, school = ${Student11.school}")
  }
}

// 1.伴生对象采用 object 关键字声明
object Student11 {
  val school: String = "scala"

  // 定义一个类的对象实例的创建方法
  def newStudent(name: String, age: Int): Student11 = new Student11(name, age)

  def apply(name: String, age: Int): Student11 = new Student11(name, age)

}
