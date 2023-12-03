package com.scala.chapter05

object Test06_HighOrderFunction {

  def main(args: Array[String]): Unit = {
    // 定义函数
    def f(n: Int): Int = {
      println("f 调用")
      n + 1
    }

    // 调用函数
    val result: Int = f(123) //f 调用
    println(result) //124

    println("=============================")


    // 1. 函数作为值进行传递
    val f1: Int => Int = f
    val result2 = f1(234) //f 调用
    println(result2) //235

    println("----------------------")

    //2. 在被调用函数 foo 后面加上 _，相当于把函数 foo 当成一个整体， 传递给变量 f1
    val f2 = f _
    val result3 = f2(345) //f 调用
    println(result3) //346

    //3. 如果明确变量类型，那么不使用下划线也可以将函数作为整体传递给 变量
    def fun(): Int = {
      println("fun 调用")
      2
    }

    val f3: () => Int = fun
    println(f3()) //2
    println("----------------------")

    val result4 = fun _
    println(result4) //com.scala.chapter05.Test06_HighOrderFunction$$$Lambda$21/0x0000000801096390@39ba5a14


    println("=============================")

    // 2. 函数作为参数进行传递
    // 定义二元计算函数
    //定义一个函数，函数参数还是一个函数签名；op 表示函数名称;(Int,Int) 表示输入两个 Int 参数；Int 表示函数返回值
    def dualEval(op: (Int, Int) => Int, a: Int, b: Int): Int = {
      op(a, b)
    }

    //定义一个函数，参数和返回值类型和 dualEval 的输入参数一致
    def add(a: Int, b: Int): Int = {
      a + b
    }

    //将 add 函数作为参数传递给 f1 函数，如果能够推断出来不是调用，_ 可以省略
    println(dualEval(add, 11, 22)) //33
    println("----------------------")
    println(dualEval((a, b) => a + b, 11, 33)) //44
    println("----------------------")
    println(dualEval(_ + _, 22, 44)) //66

    // 可以传递匿名函数

    println("=============================")

    // 3. 函数作为函数的返回值返回
    def f5(): Int => Unit = {
      def f6(a: Int): Unit = {
        println("f6调用 : " + a)
      }

      f6 // 将函数直接返回
    }

    // 因为 f5 函数的返回值依然为函数，所以可以变量 f6 可以作为函数继续调用
    val f6 = f5()
    println(f6) //com.scala.chapter05.Test06_HighOrderFunction$$$Lambda$24/0x00000008010965e0@17c68925
    f6(66) //f6调用 : 66
    println("----------------------")

    // 上面的代码可以简化为
    /*
    f6调用 : 77
    ()
     */
    println(f5()(77))
  }

}
