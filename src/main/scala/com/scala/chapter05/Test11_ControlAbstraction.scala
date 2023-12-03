package com.scala.chapter05

object Test11_ControlAbstraction {

  def main(args: Array[String]): Unit = {
    //1. 传值参数
    def f0(a: Int): Unit = {

      println("a: " + a)
      println("b: " + a)
    }

    /*
    a: 11
    b: 11
     */
    f0(11)
    println("------------------------")

    def f1(): Int = {
      println("f1调用")
      22
    }

    /*
    f1调用
    a: 22
    b: 22
     */
    f0(f1())
    println("============================")


    // 2. 传名参数，传递的不再是具体的值，而是代码块
    def f2(a: => Int): Unit = { //注意这里变量 a 没有小括号了
      println("a: " + a)
      println("a: " + a)
    }

    /*
    a: 23
    a: 23
     */
    f2(23)
    println("----------------------------")

    /*
    f1调用
    a: 22
    f1调用
    a: 22
     */
    f2(f1())
    println("----------------------------")

    // 1.传递代码块
    //上面也可以简化如下
    /*
    this is code block
    a: 33
    this is code block
    a: 33
     */
    f2({
      println("this is code block")
      33
    })

    println("----------------------------")
    //2. 小括号可以省略
    /*
    this is code block
    a: 44
    this is code block
    a: 44
     */
    f2 {
      println("this is code block")
      44
    }

  }


}
