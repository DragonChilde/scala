package com.scala.chapter07

import scala.collection.mutable.ListBuffer

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-12 10:04
 * */
object Test05_ListBuffer {

  def main(args: Array[String]): Unit = {
    // 1. 创建可变列表
    val list1: ListBuffer[Int] = new ListBuffer[Int]()
    list1.append(11, 22, 33, 44)


    val list2 = ListBuffer(11, 22, 33, 44)

    println(list1) //ListBuffer(11, 22, 33, 44)
    println(list2) //ListBuffer(11, 22, 33, 44)

    println("==============================")
    // 2. 添加元素
    // 尾部追加一元素
    list1.append(55)
    // 头部追加一元素
    list2.prepend(55)

    println(list1) //ListBuffer(11, 22, 33, 44, 55)
    println(list2) //ListBuffer(55, 11, 22, 33, 44)

    println("==============================")

    31 +=: 96 +=: list1 += 25 += 83
    println(list1) //ListBuffer(31, 96, 11, 22, 33, 44, 55, 25, 83)

    println("==============================")
    // 3. 合并list
    val list3 = list1 ++ list2
    println(list3) //ListBuffer(31, 96, 11, 22, 33, 44, 55, 25, 83, 55, 11, 22, 33, 44)
    println("==============================")

    // 前面的列表合到后面的列表
    list1 ++=: list2
    println(list1) //ListBuffer(31, 96, 11, 22, 33, 44, 55, 25, 83)
    println(list2) //ListBuffer(31, 96, 11, 22, 33, 44, 55, 25, 83, 55, 11, 22, 33, 44)

    println("==============================")
    // 4. 修改元素
    list2(0) = 10
    list2.update(1, 9)
    println(list2) //ListBuffer(10, 9, 11, 22, 33, 44, 55, 25, 83, 55, 11, 22, 33, 44)


    // 5. 删除元素
    // 删除下标2的元素
    list2.remove(2)
    // 删除元素为5,只能删除一个,首次出现
    list2 -= 55
    println(list2) //ListBuffer(10, 9, 22, 33, 44, 25, 83, 55, 11, 22, 33, 44)


  }

}
