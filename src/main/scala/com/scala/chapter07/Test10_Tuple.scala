package com.scala.chapter07

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-13 12:25
 * */
object Test10_Tuple {

  def main(args: Array[String]): Unit = {
    // 1. 声明元组的方式：(元素 1，元素 2，元素 3)
    val tuple: (String, Int, Char, Boolean) = ("hello", 100, 'a', true)
    println(tuple) //(hello,100,a,true)

    println("=====================================")

    // 2. 访问数据
    // 通过元素的顺序进行访问，调用方式：_顺序号
    println(tuple._1) //hello
    println(tuple._2) //100
    println(tuple._3) //a
    println(tuple._4) //true

    println("=====================================")

    //通过索引访问数据
    println(tuple.productElement(1)) //100
    println("=====================================")
    // 3. 遍历元组数据
    for (elem <- tuple.productIterator) {
      println(elem)
      /*
      hello
      100
      a
      true
       */
    }
    println("=====================================")
    // 4. 嵌套元组
    val mulTuple = (11, 0.1, "hello", (10, "scala"))
    println(mulTuple._4._2) //scala
    println("=====================================")

    //Map 中的键值对其实就是元组,只不过元组的元素个数为 2，称之为对偶
    val map = Map("a" -> 1, "b" -> 2, "c" -> 3)
    val map2 = Map(("a", 1), ("b", 2), ("c", 3))

    map.foreach(tuple => println(tuple._1 + "=" + tuple._2))

  }

}
