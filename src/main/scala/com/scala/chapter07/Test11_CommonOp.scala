package com.scala.chapter07

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-14 08:21
 * */
object Test11_CommonOp {

  def main(args: Array[String]): Unit = {

    val list = List(11, 22, 33, 44, 55, 66)
    val set = Set(10, 20, 30, 40, 50)

    // 1. 获取集合长度
    println(list.length) //6

    // 2. 获取集合大小
    println(set.size) //5

    println("===============================")

    // 2. 循环遍历
    for (elem <- list) {
      println(elem)
      /*
      11
      22
      33
      44
      55
      66
       */
    }
    println("===============================")


    set.foreach(println)
    /*
    10
    20
    50
    40
    30
     */

    println("===============================")
    //2. 迭代器
    for (elem <- list.iterator) println(elem)
    /*
    11
    22
    33
    44
    55
    66
     */

    println("===============================")
    //5. 生成字符串
    println(list) //List(11, 22, 33, 44, 55, 66)
    println(set) //HashSet(10, 20, 50, 40, 30)
    println(list.mkString("--")) //11--22--33--44--55--66

    println("===============================")

    //6. 是否包含
    println(list.contains(22)) //true
    println(set.contains(40)) //true
  }

}
