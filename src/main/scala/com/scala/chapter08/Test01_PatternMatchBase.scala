package com.scala.chapter08

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-15 11:33
 * */
object Test01_PatternMatchBase {

  def main(args: Array[String]): Unit = {
    // 1. 基本定义语法
    val x: Int = 5
    val y: String = x match {
      case 1 => "one"
      case 2 => "two"
      case 3 => "three"
      case _ => "other"
    }

    println(y) //other


    // 2. 示例：用模式匹配实现简单二元运算
    val a = 11
    val b = 22

    def matchDualOp(op: Char): Int = op match {
      case '+' => a + b
      case '-' => a - b
      case '*' => a * b
      case '/' => a / b
      case '%' => a % b
      case _ => -1
    }

    println(matchDualOp('+')) //33
    println(matchDualOp('/')) //0
    println(matchDualOp('\\')) //-1


    println("=========================")


    // 3. 模式守卫
    // 求一个整数的绝对值
    def abs(num: Int): Int = {
      num match {
        case i if i >= 0 => i
        case i if i < 0 => -i
      }
    }


    println(abs(10)) //10
    println(abs(0)) //0
    println(abs(-20)) //20


  }

}
