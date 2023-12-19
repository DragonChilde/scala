package com.scala.chapter07

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-14 08:37
 * */
object Test12_DerivedCollection {

  def main(args: Array[String]): Unit = {

    val list = List(11, 22, 33, 44, 55, 66)
    val list2 = List(10, 20, 30, 40, 50, 60)

    // 1. 获取集合的头
    println(list.head) //11

    //2. 获取集合的尾（不是头的就是尾）
    println(list.tail) //List(22, 33, 44, 55, 66)


    // 3. 集合最后一个数据
    println(list2.last) //60

    println("==============================")

    // 4. 集合初始数据（不包含最后一个）
    println(list2.init) //List(10, 20, 30, 40, 50)

    //5. 反转
    println(list.reverse) //List(66, 55, 44, 33, 22, 11)

    // 6. 取前（后）n个元素
    println(list.take(3)) //List(11, 22, 33)
    //从后取4个元素
    println(list.takeRight(4)) //List(33, 44, 55, 66)

    println("==============================")

    // 7. 去掉前（后）n个元素
    println(list.drop(3)) //List(44, 55, 66)
    // 从右去掉4个元素
    println(list.dropRight(4)) //List(11, 22)


    println("==============================")

    // 8. 并集
    val concat = list.concat(list2)
    println("concat: " + concat) //concat: List(11, 22, 33, 44, 55, 66, 10, 20, 30, 40, 50, 60)
    println(list ::: list2) //List(11, 22, 33, 44, 55, 66, 10, 20, 30, 40, 50, 60)


    println("==============================")

    // 如果是set做并集，会去重
    val set1 = Set(11, 22, 33, 44, 55)
    val set2 = Set(10, 20, 30, 40, 11, 22)

    val concatSet = set1.concat(set2)
    println("concat set: " + concatSet) //concat set: HashSet(10, 20, 33, 11, 30, 22, 44, 40, 55)
    println(set1 ++ set2) //HashSet(10, 20, 33, 11, 30, 22, 44, 40, 55)

    println("==============================")

    // 9.交集
    val intersect = set1.intersect(set2)
    println("intersect: " + intersect) //intersect: HashSet(22, 11)


    println("==============================")

    // 10. 差集
    val diff1 = set1.diff(set2)
    val diff2 = set2.diff(set1)
    println("diff1: " + diff1) //diff1: HashSet(33, 44, 55)
    println("diff2: " + diff2) //diff2: HashSet(10, 20, 40, 30)

    println("==============================")

    // 11. 拉链(注:如果两个集合的元素个数不相等，那么会将同等数量的数据进 行拉链，多余的数据省略不用)
    println("zip: " + list.zip(list2)) //zip: List((11,10), (22,20), (33,30), (44,40), (55,50), (66,60))
    println("zip: " + list2.zip(list)) //zip: List((10,11), (20,22), (30,33), (40,44), (50,55), (60,66))

    println("==============================")

    //  12. 滑窗
    for (elem <- list.sliding(3))
      println(elem)
    /*
    List(11, 22, 33)
    List(22, 33, 44)
    List(33, 44, 55)
    List(44, 55, 66)
     */


    println("------------------------------------")

    for (elem <- list2.sliding(4, 2))
      println(elem)

    /*
    List(10, 20, 30, 40)
    List(30, 40, 50, 60)
     */


    println("------------------------------------")

    for (elem <- list2.sliding(3, 3))
      println(elem)

    /*
    List(10, 20, 30)
    List(40, 50, 60)
     */

  }

}
