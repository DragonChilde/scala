package com.scala.chapter09

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-15 11:54
 * */
object Test01_Exception {

  def main(args: Array[String]): Unit = {

    try {
      val n = 10 / 0
    } catch {
      case e: ArithmeticException => {
        println("ArithmeticException")
      }
      case e: Exception => {
        println("Exception")
      }
    } finally {
      println("hand ending")
    }
    /*
    ArithmeticException
    hand ending
     */
  }

}
