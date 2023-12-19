package com.scala.chapter07

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-13 09:46
 * */
object Test08_ImmutableMap {

  def main(args: Array[String]): Unit = {
    // 1. 创建不可变集合 Map
    val map: Map[String, Int] = Map("a" -> 11, "b" -> 22, "c" -> 33)
    println(map) //Map(a -> 11, b -> 22, c -> 33)
    println(map.getClass) //class scala.collection.immutable.Map$Map3

    println("===============================")
    // 2. 遍历元素

    for (key <- map.keys) {
      // 使用 get 访问 map 集合的数据，会返回特殊类型 Option(选项): 有值 （Some ），无值(None)
      println(key + "=" + map.get(key).get)
      /*
      a=11
      b=22
      c=33
       */
    }


    println("---------------------")


    map.foreach(println)
    /*
    (a,11)
    (b,22)
    (c,33)
     */
    println("---------------------")

    map.foreach((kv: (String, Int)) => println(kv))
    /*
    (a,11)
    (b,22)
    (c,33)
     */

    println("===============================")
    // 3. 取map中所有的key 或者 value
    for (key <- map.keys) {
      println(s"$key---> ${map.get(key)}")
      // 注意这里的Some类型是Option的子类
      /*
      a---> Some(11)
      b---> Some(22)
      c---> Some(33)
       */
    }

    println("===============================")

    // 4. 访问某一个key的value
    println("a : " + map.get("a").get) //a : 11
    println("c : " + map.get("c")) //c : Some(33)
    //如果 key 不存在，返回 0
    println("b : " + map.getOrElse("b", 0)) //b : 22


    println("--------------------------------")
    println(map("a")) //1

  }

}
