package com.scala.chapter04

import scala.language.postfixOps
import scala.math.BigDecimal.double2bigDecimal

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-11-23 11:14
 * */
object Test02_ForLoop {

  def main(args: Array[String]): Unit = {
    // java for语法： for(int i = 0; i < 10; i++){ System.out.println(i + ". hello world") }
    // 1. 范围遍历
    for (i <- 1 to 3) {
      println(i + " hello world")
    }
    // 与上面等价
    for (i: Int <- 1.to(3)) {
      println(i + " hello world")
    }

    println("===========================")

    /* for (i <- Range(1, 3)) {
       println(i + " hello world")
     }*/
    // 与上面等价
    for (i <- 1 until (3)) {
      println(i + " hello world")
    }

    println("==============================")


    // 2.集合
    for (i <- Array(11, 22, 33)) {
      println(i)
    }

    for (i <- List(22, 33, 44)) {
      println(i)
    }
    for (i <- Set(33, 44, 55)) {
      println(i)
    }

    println("==========================")

    // 3. 循环守卫
    for (i <- 1 to 10) {
      if (i != 5) {
        println(i)
      }
    }

    for (i <- 1 to 10 if i != 5) {
      println(i)
    }

    println("======================")
    // 4. 循环步长
    for (i <- 1 to 10 by 2) {
      println(i)
    }
    println("-------------------")
    for (i <- 30 to 13 by -2) {
      println(i)
    }
    println("-------------------")
    for (i <- 10 to 1 by -1) {
      println(i)
    }
    println("-------------------")

    // error，step不能为0
    /*
    for (i <- 30 to 13 by 0) {
          println(i)
        }
        */

    for (i <- 1.0 to 10.0 by 0.3) {
      println(i)
    }

    println("======================")

    // 5. 循环嵌套
    for (i <- 1 to 3) {
      for (j <- 1 to 3) {
        println("i = " + i + " j = " + j)
      }
    }
    println("-------------------")
    for (i <- 1 to 3; j <- 1 to 3) {
      println("i = " + i + " j = " + j)
    }

    println("======================")

    // 6. 循环引入变量
    for (i <- 1 to 10) {
      val j = 10 - i
      println("i = " + i + " j = " + j)
    }
    println("-------------------")
    for (i <- 1 to 10; j = 10 - i) {
      println("i = " + i + " j = " + j)
    }

    println("-------------------")

    for {i <- 1 to 10
         j = 10 - i
         } {
      println("i = " + i + " j = " + j)
    }

    println("======================")
    // 7. 循环返回值
    val a = for (i <- 1 to 10) {
      i
    }
    println("a = " + a)

    val b = for (i <- 1 to 10) yield i * i
    println(b) //Vector(1, 4, 9, 16, 25, 36, 49, 64, 81, 100)
    println("======================")


    for (i <- 1 to 10 reverse) {
      println(i)
    }

  }

}
