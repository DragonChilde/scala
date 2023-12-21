package com.scala.chapter09

import scala.language.implicitConversions

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-15 11:54
 * */
object Test02_Implicit {

  def main(args: Array[String]): Unit = {


    val myRichInt = new MyRichInt(11)
    println(myRichInt.myMax(22)) //22

    println("===================================")

    // 1. 隐式函数
    // 使用 implicit 关键字声明的函数称之为隐式函数
    implicit def convert(num: Int): MyRichInt = new MyRichInt(num)

    // 当想调用对象功能时，如果编译错误，那么编译器会尝试在当前作用域范围内查找能调用对应功能的转换规则
    // 这个调用过程是由编译器完成的 ，所以称之为隐式转换 。也称之为自动转换
    println(11.myMax(22)) //22

    println("===================================")
    // 2. 隐式类
    implicit class MyRichInt2(val self: Int) {
      // 自定义比较大小的方法
      def myMax2(n: Int): Int = if (n < self) self else n

      def myMin2(n: Int): Int = if (n < self) n else self
    }

    println(22.myMax2(11)) //22

    println("===================================")

    // 3. 隐式参数
    implicit val str: String = "jack"
    implicit val num: Int = 22

    def sayHello()(implicit name: String): Unit = {
      println("hello, " + name)
    }

    def sayHi(implicit name: String = "terry"): Unit = {
      println("hi , " + name)
    }

    sayHello
    sayHello()("tom") //hello, jack
    sayHi //hi , jack

    println("===================================")


    // 简便写法
    def hiAge(): Unit = {
      println("hi , " + implicitly[Int])
    }

    hiAge() //hi , 22

  }

}


// 自定义类
class MyRichInt(val self: Int) {

  // 自定义比较大小的方法
  def myMax(n: Int): Int = if (n < self) self else n

  def myMin(n: Int): Int = if (n < self) n else self
}
