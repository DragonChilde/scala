package com.scala.chapter07

import scala.collection.parallel.CollectionConverters.seqIsParallelizable
import scala.collection.parallel.ParSeq


/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-14 19:17
 * */
object Test20_Parallel {

  def main(args: Array[String]): Unit = {


    val result: IndexedSeq[Long] = (1 to 100).map(x => Thread.currentThread().getId)

    // 可以看到主要在main线程处理
    println(result)
    //Vector(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)


    val result2: ParSeq[Long] = (1 to 100).par.map(x => Thread.currentThread().getId)

    // 可看到线程ID发生了变化
    println(result2)
    //ParVector(16, 16, 16, 23, 23, 23, 21, 21, 21, 21, 21, 21, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 18, 18, 18, 27, 27, 27, 22, 22, 22, 22, 22, 22, 20, 20, 20, 25, 18, 18, 25, 25, 25, 16, 16, 23, 23, 17, 17, 17, 19, 27, 27, 16, 27, 27, 17, 19, 19, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 21, 27, 27, 23, 19, 19, 24, 21, 21, 27, 27, 23, 23)


  }

}
