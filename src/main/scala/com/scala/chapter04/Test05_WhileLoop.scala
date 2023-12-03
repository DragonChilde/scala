package com.scala.chapter04

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-11-24 21:08
 * */
object Test05_WhileLoop {

  def main(args: Array[String]): Unit = {
    var a: Int = 5
    while (a >= 1) {
      println("this is a while loop: " + a)
      a -= 1
    }

    var b: Int = 0
    do {

      println("this is a do-while loop: " + b)
      b -= 1
    } while (b > 0)
  }

}
