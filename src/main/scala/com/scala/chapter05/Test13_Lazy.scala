package com.scala.chapter05

object Test13_Lazy {

  def main(args: Array[String]): Unit = {

    val result: Int = sum(11, 22)

    /*
    3. sum调用
    1. 函数调用
    2. result = 33
    4. result = 33
     */
    println("1. 函数调用")
    println("2. result = " + result)
    println("4. result = " + result)

    println("======================")

    lazy val result2: Int = sum(11, 22)

    /*
    1. 函数调用
    3. sum调用
    2. result = 33
    4. result = 33
     */
    println("1. 函数调用")
    println("2. result = " + result2)
    println("4. result = " + result2)
  }

  def sum(a: Int, b: Int): Int = {
    println("3. sum调用")
    a + b
  }

}
