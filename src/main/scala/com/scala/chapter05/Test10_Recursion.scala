package com.scala.chapter05

import scala.annotation.tailrec

object Test10_Recursion {

  def main(args: Array[String]): Unit = {
    println(fact(5)) //120

    println(tailFact(5)) //120
  }

  // 阶乘
  // 递归算法
  // 1) 方法调用自身
  // 2) 方法必须要有跳出的逻辑
  // 3) 方法调用自身时，传递的参数应该有规律
  // 4) scala 中的递归必须声明函数返回值类型

  // 递归实现计算阶乘
  def fact(n: Int): Int = {
    if (n == 0) return 1
    fact(n - 1) * n
  }

  /*
  递归缺点就是不断在栈空间里创建函数,造成内存资源浪费,直到return才会进行销毁
  尾递归解决了内存资源浪费的问题,把每次的计算结果直接存在变量里,计算完可直接销毁方法线空间的内存
   */
  // 尾递归实现
  def tailFact(n: Int): Int = {
    //尾递归特有的注解,只在尾递归函数里才会有作用
    @tailrec
    def loop(n: Int, currRes: Int): Int = {
      if (n == 0) return currRes
      loop(n - 1, currRes * n)
    }

    loop(n, 1)
  }
}
