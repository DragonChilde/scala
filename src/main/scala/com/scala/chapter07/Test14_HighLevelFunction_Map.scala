package com.scala.chapter07

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-14 11:35
 * */
object Test14_HighLevelFunction_Map {

  def main(args: Array[String]): Unit = {
    val list = List(11, 22, 33, 44, 55, 66, 77)

    // 1. 过滤
    // 选取偶数
    val evenList = list.filter((elem: Int) => {
      elem % 2 == 0
    })
    println(evenList) //List(22, 44, 66)

    // 选取奇数
    println(list.filter(_ % 2 == 1)) //List(11, 33, 55, 77)

    println("======================================")

    // 2. 映射map
    // 把集合中每个数乘2
    println(list.map(_ * 2)) //List(22, 44, 66, 88, 110, 132, 154)
    println(list.map(x => x * x)) //List(121, 484, 1089, 1936, 3025, 4356, 5929)

    println("======================================")


    // 3. 扁平化
    val nestedList: List[List[Int]] = List(List(1, 2, 3), List(4, 5), List(6, 7, 8, 9))
    val flatList = nestedList(0) ::: nestedList(1) ::: nestedList(2)
    println(flatList) //List(1, 2, 3, 4, 5, 6, 7, 8, 9)

    val flatList2 = nestedList.flatten
    println(flatList2) //List(1, 2, 3, 4, 5, 6, 7, 8, 9)

    println("======================================")

    // 4. 扁平映射
    // 将一组字符串进行分词，并保存成单词的列表
    val strings: List[String] = List("hello world", "hello scala", "hello java", "we study")
    val splitList: List[Array[String]] = strings.map(_.split(" ")) // 分词
    val flattenList = splitList.flatten // 打散扁平化

    println(flattenList) //List(hello, world, hello, scala, hello, java, we, study)

    // 上面的方式也可以简写如下
    // 扁平化+映射 注：flatMap 相当于先进行 map 操作，在进行 flatten 操作
    val flatmapList = strings.flatMap(_.split(" "))
    println(flatmapList) //List(hello, world, hello, scala, hello, java, we, study)


    println("======================================")

    // 5. 分组groupBy
    // 分成奇偶两组
    val groupMap: Map[Int, List[Int]] = list.groupBy(_ % 2)
    val groupMap2: Map[String, List[Int]] = list.groupBy(data => if (data % 2 == 0) "偶数" else "奇数")

    println(groupMap) //HashMap(0 -> List(22, 44, 66), 1 -> List(11, 33, 55, 77))
    println(groupMap2) //HashMap(偶数 -> List(22, 44, 66), 奇数 -> List(11, 33, 55, 77))

    println("======================================")

    // 给定一组词汇，按照单词的首字母进行分组
    val worldList = List("china", "america", "alice", "canada", "cary", "bob", "japan")
    println(worldList.groupBy(_.charAt(0))) //HashMap(j -> List(japan), a -> List(america, alice), b -> List(bob), c -> List(china, canada, cary))

  }

}
