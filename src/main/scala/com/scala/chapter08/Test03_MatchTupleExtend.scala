package com.scala.chapter08

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-15 11:34
 * */
object Test03_MatchTupleExtend {

  def main(args: Array[String]): Unit = {

    // 1. 在变量声明时匹配
    val (x, y) = (10, "hello")
    println(s"x: $x , y: $y") //x: 10 , y: hello

    val List(first, second, _*) = List(11, 22, 33, 44)
    println(s"first : $first , second: $second") //first : 11 , second: 22

    val fir :: sec :: rest = List(10, 20, 30.50)
    println(s"first : $fir , second: $sec, rest: $rest") //first : 10.0 , second: 20.0, rest: List(30.5)

    println("===========================================")

    // 2. for推导式中进行模式匹配
    val list: List[(String, Int)] = List(("a", 11), ("b", 20), ("c", 33), ("d", 40))
    // 2.1 原本的遍历方式
    for (elem <- list) {
      println(elem._1 + " " + elem._2)
      /*
      a 11
      b 20
      c 33
      d 40
       */
    }
    println("--------------------------")
    // 2.2 将List的元素直接定义为元组，对变量赋值
    for ((word, count) <- list) {
      println(word + ": " + count)
      /*
      a: 11
      b: 20
      c: 33
      d: 40
       */
    }

    println("--------------------------")

    // 2.3 可以不考虑某个位置的变量，只遍历key或者value
    for ((word, _) <- list)
      println(word)
    /*
    a
    b
    c
    d
     */

    println("--------------------------")

    // 2.4 可以指定某个位置的值必须是多少
    for (("a", count) <- list)
      println(count) //11

  }

}
