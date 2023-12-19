package com.scala.chapter07

import scala.collection.mutable

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-13 10:28
 * */
object Test09_MutableMap {

  def main(args: Array[String]): Unit = {
    // 1. 创建可变集合
    val mutableMap: mutable.Map[String, Int] = mutable.Map("a" -> 11, "b" -> 22, "c" -> 33)
    println(mutableMap) //HashMap(a -> 11, b -> 22, c -> 33)
    println(mutableMap.getClass) //class scala.collection.mutable.HashMap


    println("===================================")
    // 2. 添加元素
    mutableMap.put("d", 44)
    mutableMap.put("e", 55)
    println(mutableMap) //HashMap(a -> 11, b -> 22, c -> 33, d -> 44, e -> 55)

    mutableMap += (("f", 66))
    mutableMap += ("g" -> 77)
    println(mutableMap) //HashMap(a -> 11, b -> 22, c -> 50, e -> 10, f -> 66, g -> 77)

    println("===================================")

    // 3. 删除元素
    println(mutableMap.get("c")) //Some(33)
    mutableMap.remove("c")
    println(mutableMap.getOrElse("c", 0)) //0

    mutableMap -= "d"
    mutableMap -= ("g", "c")
    println(mutableMap) //HashMap(a -> 11, b -> 22, e -> 55, f -> 66)
    println("===================================")

    // 4. 修改元素
    mutableMap.update("c", 50)
    mutableMap("e") = 10
    println(mutableMap) //HashMap(a -> 11, b -> 22, c -> 50, e -> 10, f -> 66)

    println("===================================")

    // 5. 合并两个Map
    val mutableMap2: mutable.Map[String, Int] = mutable.Map("aaa" -> 11, "b" -> 20, "hello" -> 44)
    mutableMap ++= mutableMap2
    println(mutableMap) //HashMap(aaa -> 11, a -> 11, b -> 20, c -> 50, e -> 10, f -> 66, hello -> 44)
    println(mutableMap2) //HashMap(aaa -> 11, b -> 20, hello -> 44)

    println("---------------------------")

    val mutableMap3: mutable.Map[String, Int] = mutableMap ++ mutableMap2
    println(mutableMap) //HashMap(aaa -> 11, a -> 11, b -> 20, c -> 50, e -> 10, f -> 66, hello -> 44)
    println(mutableMap2) //HashMap(aaa -> 11, b -> 20, hello -> 44)
    println(mutableMap3) //HashMap(aaa -> 11, a -> 11, b -> 20, c -> 50, e -> 10, f -> 66, hello -> 44)

    println("==============================")

    // 打印集合
    mutableMap.foreach(kv => println(kv))
    /*
    (aaa,11)
    (a,11)
    (b,20)
    (c,50)
    (e,10)
    (f,66)
    (hello,44)
     */

  }

}
