package com.scala.chapter04

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-11-23 19:44
 * */
object Test04_Practice_Pyramid {

  def main(args: Array[String]): Unit = {
    for (i <- 1 to 9) {
      val stars = 2 * i - 1
      val spaces = 9 - i
      println(" " * spaces + "*" * stars)
    }

    // 简写,其效果与上一样
    for (i <- 1 to 9; stars = 2 * i - 1; spaces = 9 - i) {
      println(" " * spaces + "*" * stars)
    }

    // 继续优化,与上效果一样
    for (stars <- 1 to 17 by 2; spaces = (17 - stars) / 2) {
      println(" " * spaces + "*" * stars)
    }

  }

}
