package com.scala.chapter07

import scala.collection.immutable.Queue
import scala.collection.mutable

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-14 19:05
 * */
object Test19_Queue {

  def main(args: Array[String]): Unit = {
    // 创建一个可变队列
    val queue: mutable.Queue[String] = new mutable.Queue[String]()

    queue.enqueue("a", "b", "c")

    println(queue) //Queue(a, b, c)
    println(queue.dequeue()) //a

    println(queue) //Queue(b, c)
    println(queue.dequeue()) //b
    println(queue) //Queue(c)

    println("-----------------------------------")

    queue.enqueue("d", "e")
    println(queue) //Queue(c, d, e)
    println(queue.dequeue()) //c
    println(queue) //Queue(d, e)

    println("=========================================")

    // 不可变队列
    val queue2: Queue[String] = Queue("a", "b", "c")
    val queue3 = queue2.enqueue("d")
    println(queue2) //Queue(a, b, c)
    println(queue3) //Queue(a, b, c, d)

  }

}
