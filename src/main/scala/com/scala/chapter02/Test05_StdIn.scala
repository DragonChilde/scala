package com.scala.chapter02

import scala.io.StdIn

object Test05_StdIn {

  def main(args: Array[String]): Unit = {
    // 输入信息
    println("please enter your name:")
    val name = StdIn.readLine()

    println("please enter your age:")
    val age = StdIn.readInt()

    // 控制台打印输出
    println(s"welcome ${age} old ${name} come here!")
  }
}
