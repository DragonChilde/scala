package com.scala.chapter07

import scala.collection.mutable

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-13 09:07
 * */
object Test07_MutableSet {

  def main(args: Array[String]): Unit = {
    // 1. 创建可变集合
    val mutableSet: mutable.Set[Int] = mutable.Set(11, 22, 33, 44, 55, 66, 11, 22, 33)
    println(mutableSet) //HashSet(33, 66, 22, 55, 11, 44)

    println("========================")

    // 2. 添加元素
    val mutableSet2 = mutableSet + 77
    println(mutableSet) //HashSet(33, 66, 22, 55, 11, 44)
    println(mutableSet2) //HashSet(33, 66, 22, 55, 11, 44, 77)

    mutableSet += 77
    println(mutableSet) //HashSet(33, 66, 22, 55, 11, 44, 77)

    println("========================")

    val flag = mutableSet.add(10)
    println(flag) //true
    println(mutableSet) //HashSet(33, 66, 22, 55, 10, 11, 44, 77)

    val flag2 = mutableSet.add(10)
    println(flag2) //false
    println(mutableSet) //HashSet(33, 66, 22, 55, 10, 11, 44, 77)


    println("========================")

    // 3. 删除元素
    mutableSet -= 11
    println(mutableSet) //HashSet(33, 66, 22, 55, 10, 44, 77)

    val flag3 = mutableSet.remove(10)
    println(flag3) //true
    println(mutableSet) //HashSet(33, 66, 22, 55, 44, 77)

    val flag4 = mutableSet.remove(10)
    println(flag4) //false
    println(mutableSet) //HashSet(33, 66, 22, 55, 44, 77)

    println("========================")

    // 4. 合并两个Set
    val mutableSet3 = mutable.Set(10, 20, 30, 40)
    val mutableSet4 = mutableSet ++ mutableSet3
    println(mutableSet4) //HashSet(33, 66, 20, 22, 55, 40, 10, 44, 77, 30)
    println(mutableSet) //HashSet(33, 66, 22, 55, 44, 77)
    println(mutableSet3) //HashSet(33, 66, 22, 55, 11, 44, 77)

    println("========================")
    mutableSet3 ++= mutableSet
    println(mutableSet) //HashSet(33, 66, 22, 55, 44, 77)
    println(mutableSet3) //HashSet(33, 66, 20, 22, 55, 40, 10, 44, 77, 30)

    println("========================")
    // 打印集合
    mutableSet.foreach(println)
    /*
    33
    66
    22
    55
    44
    77
     */

  }

}
