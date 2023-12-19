package com.scala.chapter08

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-15 11:35
 * */
object Test06_PartialFunction {

  def main(args: Array[String]): Unit = {
    val list: List[(String, Int)] = List(("a", 11), ("b", 22), ("c", 33), ("d", 10))

    // 1. map转换，实现key不变，value2倍
    val newList = list.map(tuple => (tuple._1, tuple._2 * 2))

    // 2. 用模式匹配对元组元素赋值，实现功能
    val newList2 = list.map(
      tuple => {
        tuple match {
          case (word, count) => (word, count * 2)
        }
      }
    )

    // 3. 省略lambda表达式的写法，进行简化
    val newList3 = list.map {
      case (word, count) => (word, count * 2)
    }

    println(newList) //List((a,22), (b,44), (c,66), (d,20))
    println(newList2) //List((a,22), (b,44), (c,66), (d,20))
    println(newList3) //List((a,22), (b,44), (c,66), (d,20))

    println("====================================")

    // 偏函数的应用，求绝对值
    // 对输入数据分为不同的情形：正、负、0
    val positiveAbs: PartialFunction[Int, Int] = {
      case x if x > 0 => x
    }
    val negativeAbs: PartialFunction[Int, Int] = {
      case x if x < 0 => -x
    }

    val zeroAbs: PartialFunction[Int, Int] = {
      case 0 => 0
    }

    def abs(x: Int): Int = (positiveAbs orElse negativeAbs orElse zeroAbs)(x)

    println(abs(-67)) // 67
    println(abs(11)) //11
    println(abs(0)) //0

  }

}
