package com.scala.chapter07

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-14 16:19
 * */
object Test15_HighLevelFunction_Reduce {

  def main(args: Array[String]): Unit = {

    val list = List(11, 22, 33, 44, 55, 66)

    // 1. reduce
    // 将数据两两结合，实现运算规则
    println(list.reduce((x, y) => x + y)) //231
    // 上面的方式也可简写为
    println(list.reduce(_ + _)) //231
    // 从源码的角度，reduce 底层调用的其实就是 reduceLeft
    println(list.reduceLeft(_ + _)) //231
    println(list.reduceRight(_ + _)) //231

    println("===============================")

    val list2 = List(10, 20, 30, 40, 50)
    println(list2.reduce(_ - _)) //-130
    println(list2.reduceLeft(_ - _)) //-130

    // (10-(20 - (30 - (40 - 50))
    println(list2.reduceRight(_ - _)) //30

    println("===============================")

    // 2. fold
    // fold 方法使用了函数柯里化，存在两个参数列表
    // 第一个参数列表为 ： 零值（初始值）
    // 第二个参数列表为： 简化规则
    println(list.fold(10)(_ + _)) //241

    // fold 底层其实为 foldLeft
    // 10 - 11 - 22- 33 - 44 - 55 - 66
    println(list.foldLeft(10)(_ - _)) //-221

    // 11 - (10-(20 - (30 - (40 - 50))
    println(list2.foldRight(11)(_ - _)) //19


  }

}
