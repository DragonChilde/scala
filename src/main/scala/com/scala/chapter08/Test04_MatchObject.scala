package com.scala.chapter08

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-15 11:35
 * */
object Test04_MatchObject {

  def main(args: Array[String]): Unit = {
    val student =  Student("zhangsan", 11)

    // 针对对象实例的内容进行匹配
    val result = student match {
      case Student("zhangsan", 11) => "zhangsan , 11"
      case _ => "Else"
    }

    println(result) //zhangsan , 11
  }

}

// 定义类
class Student(val name: String, val age: Int)

// 定义伴生对象
object Student {
  def apply(name: String, age: Int): Student = new Student(name, age)

  // 必须实现一个unapply方法，用来对对象属性进行拆解
  def unapply(student: Student): Option[(String, Int)] = {
    if (student == null) {
      None
    } else {
      Some((student.name, student.age))
    }
  }
}