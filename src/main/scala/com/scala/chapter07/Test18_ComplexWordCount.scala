package com.scala.chapter07

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-14 19:04
 * */
object Test18_ComplexWordCount {

  def main(args: Array[String]): Unit = {

    val tupleList: List[(String, Int)] = List(
      ("hello", 1),
      ("hello world", 2),
      ("hello scala", 3),
      ("hello spark from scala", 1),
      ("hello flink from scala", 2)
    )

    // 思路一：直接展开为普通版本
    // 第一种方式（不通用）
    val newStringList: List[String] = tupleList.map(
      kv => {
        (kv._1.trim + " ") * kv._2
      }
    )

    println(newStringList) //List(hello , hello world hello world , hello scala hello scala hello scala , hello spark from scala , hello flink from scala hello flink from scala )


    // 接下来操作与普通版本完全一致
    val wordCountList: List[(String, Int)] = newStringList
      .flatMap(_.split(" ")) // 空格分词
      .groupBy(word => word) // 按照单词分组
      //在 map 中，如果传进来什么就返回什么，不要用_省略
      .map(kv => (kv._1, kv._2.size)) // 统计出每个单词的个数
      .toList
      .sortBy(_._2)(Ordering[Int].reverse)
      .take(3)


    println(wordCountList) //List((hello,9), (scala,6), (from,3))

    println("===============================")


    // 思路二：直接基于预统计的结果进行转换
    // 1. 将字符串打散为单词，并结合对应的个数包装成二元组
    val preCountList: List[(String, Int)] = tupleList.flatMap(
      tuple => {
        val strings: Array[String] = tuple._1.split(" ")
        strings.map(word => (word, tuple._2))
      }
    )

    println(preCountList) //List((hello,1), (hello,2), (world,2), (hello,3), (scala,3), (hello,1), (spark,1), (from,1), (scala,1), (hello,2), (flink,2), (from,2), (scala,2))


    // 2. 对二元组按照单词进行分组
    val preCountMap: Map[String, List[(String, Int)]] = preCountList.groupBy(_._1)
    println(preCountMap) //HashMap(world -> List((world,2)), flink -> List((flink,2)), spark -> List((spark,1)), hello -> List((hello,1), (hello,2), (hello,3), (hello,1), (hello,2)), scala -> List((scala,3), (scala,1), (scala,2)), from -> List((from,1), (from,2)))


    // 3. 叠加每个单词预统计的个数值
    val countMap: Map[String, Int] = preCountMap.view.mapValues(tupleList => tupleList.map(_._2).sum).toMap

    println(countMap) //HashMap(world -> 2, flink -> 2, spark -> 1, hello -> 9, scala -> 6, from -> 3)

    // 4. 转换成list，排序取前3
    val countList = countMap.toList
      .sortWith(_._2 > _._2)
      .take(3)

    println(countList) //List((hello,9), (scala,6), (from,3))

  }

}
