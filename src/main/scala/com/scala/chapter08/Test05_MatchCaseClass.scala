package com.scala.chapter08

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-15 11:35
 * */
object Test05_MatchCaseClass {

  def main(args: Array[String]): Unit = {


    val user = User("jack", 20)

    // 针对对象实例的内容进行匹配

    val result = user match {
      case User("jack", 20) => "Jack , 20"
      case _ => "Else"
    }

    println(result) //Jack , 20
  }

}

// 定义样例类
case class User(name: String, age: Int)
