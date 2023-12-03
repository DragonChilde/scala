package com.scala.chapter04

import scala.util.control.Breaks
import scala.util.control.Breaks.break

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-11-24 21:16
 * */
object Test06_Break {

  def main(args: Array[String]): Unit = {
    // 1. 采用抛出异常的方式，退出循环
    try {
      for (i <- 0 until 5) {

        if (i == 3)
          throw new RuntimeException
        println(i)
      }
    } catch {
      case e: Exception => // 什么都不做，只是退出循环
    }

    println("-----------------------------------")

    // 2. 使用Scala中的Breaks类的break方法，实现异常的抛出和捕捉
    Breaks.breakable(
      for (i <- 0 until 5) {
        if (i == 3)
          Breaks.break()
        println(i)
      }

    )
    println("-----------------------------------")

    Breaks.breakable(
      for (i <- 0 until 5) {
        if (i == 3)
          break()
        println(i)
      }
    )


  }
}
