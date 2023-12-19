package com.scala.chapter07

import scala.collection.mutable

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-14 18:58
 * */
object Test16_MergeMap {

  def main(args: Array[String]): Unit = {
    val map = Map("a" -> 1, "b" -> 3, "c" -> 7)
    val mutableMap = mutable.Map("a" -> 22, "b" -> 33, "c" -> 44, "d" -> 55)

    println(map ++ mutableMap) //Map(a -> 22, b -> 33, c -> 44, d -> 55)


    val mergeMap = map.foldLeft(mutableMap)((result, kv) => {
      val key = kv._1
      val value = kv._2
      result(key) = result.getOrElse(key, 0) + value
      result

    })

    println(mergeMap) //HashMap(a -> 23, b -> 36, c -> 51, d -> 55)


  }

}
