package com.scala.chapter07

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-14 10:05
 * */
object Test13_SimpleFunction {

  def main(args: Array[String]): Unit = {


    val list = List(1, 2, 3, 4, 5, 6)
    val list2 = List(("a", 10), ("b", 20), ("c", 30), ("d", 40), ("e", 50), ("f", -1))

    // 1. 求和
    var sum = 0
    for (elem <- list)
      sum += elem

    println(sum) //21


    //2. 求乘积
    println(list.product) //720

    println("===================================")

    // 2. 最大值
    println(list.max) //6
    println(list2.maxBy((tuple: (String, Int)) => tuple._2)) //(e,50)
    // 上面的方法可以简写如下
    println(list2.maxBy(_._2)) //(e,50)

    println("===================================")

    //4. 最小值
    println(list.min) //1
    println(list2.minBy(_._2)) //(f,-1)

    println("===================================")


    //5. 排序
    // 5.1 sorted
    val sortedList = list.sorted
    println(sortedList) //List(1, 2, 3, 4, 5, 6)

    // 从大到小逆序排序
    println(list.sorted.reverse) //List(6, 5, 4, 3, 2, 1)

    // 传入隐式参数
    println(list.sorted(Ordering[Int].reverse)) //List(6, 5, 4, 3, 2, 1)

    // sorted对有内部多个List是不起作用的
    println(list2.sorted) //List((a,10), (b,20), (c,30), (d,40), (e,50), (f,-1))

    println("-----------------------------------------")

    // 5.2 sortBy
    println(list2.sortBy(_._2)) //List((f,-1), (a,10), (b,20), (c,30), (d,40), (e,50))
    println(list2.sortBy(_._2)(Ordering[Int].reverse)) //List((e,50), (d,40), (c,30), (b,20), (a,10), (f,-1))

    println("-----------------------------------------")
    // 5.3 sortWith
    println(list.sortWith((a: Int, b: Int) => {
      a < b
    })) //List(1, 2, 3, 4, 5, 6)

    // 上面的方式可以简写如下
    println(list.sortWith(_ < _)) //List(1, 2, 3, 4, 5, 6)

    println(list.sortWith(_ > _)) //List(6, 5, 4, 3, 2, 1)
  }

}
