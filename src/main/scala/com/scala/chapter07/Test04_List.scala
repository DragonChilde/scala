package com.scala.chapter07

object Test04_List {

  def main(args: Array[String]): Unit = {
    // 1. 创建一个List
    val list = List(11, 22, 33)
    println(list) //List(11, 22, 33)

    println("======================")

    // 2. 访问和遍历元素
    println(list(1)) //22
    list.foreach(println)
    /*
    22
    11
    22
    33
     */
    println("======================")
    // 3. 添加元素
    // 向前添加元素
    val list2 = 10 +: list
    // 向后添加元素
    val list3 = list :+ 23

    println(list) //List(11, 22, 33)
    println(list2) //List(10, 11, 22, 33)
    println(list3) //List(11, 22, 33, 23)

    println("======================")

    // 向前添加元素
    val list4 = list2.::(51)
    println(list4) //List(51, 10, 11, 22, 33)

    // 添加一个新List,元素只有一个(空集合 Nil)
    val list5 = Nil.::(13)
    println(list5) //List(13)

    val list6 = 73 :: 32 :: Nil
    println(list6) //List(73, 32)

    val list7 = 17 :: 28 :: 59 :: 16 :: Nil
    println(list7) //List(17, 28, 59, 16)

    println("======================")

    val list8 = 31 :: list2
    println(list8) //List(31, 10, 11, 22, 33)

    println("=================")


    // 4. 合并列表
    // 合并后List会在List列表里
    val list9 = list6 :: list7
    println(list9) //List(List(73, 32), 17, 28, 59, 16)

    // 合并两个list为一个List,将一个整体拆成一个一个的个体，称为扁平化
    val list10 = list6 ::: list7
    println(list10) // List(73, 32, 17, 28, 59, 16)

    // 合并同上
    val list11 = list6 ++ list7
    println(list11) //List(73, 32, 17, 28, 59, 16)


  }

}
