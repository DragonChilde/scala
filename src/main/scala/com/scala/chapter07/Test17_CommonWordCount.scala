package com.scala.chapter07

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-14 19:04
 * */
object Test17_CommonWordCount {

  def main(args: Array[String]): Unit = {

    // 单词计数：将集合中出现的相同的单词，进行计数，取计数排名前三的结果
    val stringList: List[String] = List(
      "hello",
      "hello world",
      "hello scala",
      "hello spark from scala",
      "hello flink from scala",
      "hello haddop"
    )

    // 1. 对字符串进行切分，得到一个打散所有单词的列表
    /*
    val wordList: List[Array[String]] = stringList.map(_.split(" "))
    val wordList2: List[String] = wordList.flatten
    println(wordList2) //List(hello, hello, world, hello, scala, hello, spark, from, scala, hello, flink, from, scala, hello, haddop)
 */

    // 上面的方式也可以简写为如下
    val wordList = stringList.flatMap(_.split(" "))
    println(wordList) //List(hello, hello, world, hello, scala, hello, spark, from, scala, hello, flink, from, scala, hello, haddop)


    // 2. 相同的单词进行分组,
    val groupMap: Map[String, List[String]] = wordList.groupBy(word => word)
    println(groupMap) //HashMap(world -> List(world), flink -> List(flink), haddop -> List(haddop), spark -> List(spark), hello -> List(hello, hello, hello, hello, hello, hello), scala -> List(scala, scala, scala), from -> List(from, from))


    // 3. 对分组之后的list取长度，得到每个单词的个数
    // (word, list) => (word, count)
    val countMap: Map[String, Int] = groupMap.map(kv => (kv._1, kv._2.size))
    println(countMap) //HashMap(world -> 1, flink -> 1, haddop -> 1, spark -> 1, hello -> 6, scala -> 3, from -> 2)

    //4.  对计数完成后的结果进行排序（降序）, 对排序后的结果取前 3 名
    val sortList: List[(String, Int)] = countMap.toList.sortWith(_._2 > _._2).take(3)

    println(sortList) //List((hello,6), (scala,3), (from,2))

  }

}
