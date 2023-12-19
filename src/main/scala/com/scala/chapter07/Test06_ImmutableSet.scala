package com.scala.chapter07

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-12 10:42
 * */
object Test06_ImmutableSet {


  def main(args: Array[String]): Unit = {
    // 1. 创建set(Set 默认是不可变集合，数据无序)(数据不可重复)
    val set1 = Set(11, 22, 33, 44, 55, 11, 33)
    println(set1) //HashSet(33, 11, 55, 22, 44)

    println("===========================")

    // 2. 添加元素,顺序是无序的
    val set2 = set1 + 66
    println(set1) //HashSet(33, 11, 55, 22, 44)
    println(set2) //HashSet(33, 66, 11, 55, 22, 44)

    println("===========================")

    // 3. 合并set
    val set3 = Set(12, 13, 14, 15, 16, 17)
    val set4 = set2 ++ set3
    println(set2) //HashSet(33, 66, 11, 55, 22, 44)
    println(set3) //HashSet(14, 13, 17, 12, 16, 15)
    println(set4) //HashSet(14, 33, 13, 17, 12, 66, 16, 11, 55, 15, 22, 44)

    println("===========================")

    // 4. 删除元素
    val set5 = set3 - 13
    println(set3) //HashSet(14, 13, 17, 12, 16, 15)
    println(set5) //HashSet(14, 17, 12, 16, 15)

    println("===========================")

    // 遍历集合
    for (x <- set5) {
      println(x)
      /*
      14
      17
      12
      16
      15
       */
    }
  }
}
