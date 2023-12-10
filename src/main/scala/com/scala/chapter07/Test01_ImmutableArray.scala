package com.scala.chapter07

object Test01_ImmutableArray {

  def main(args: Array[String]): Unit = {
    // 1. 创建数组
    val arr: Array[Int] = new Array[Int](5)
    println(arr.length) //5
    println("====================")
    // 2. 访问元素
    println(arr(0)) //0
    //println(arr(5)) // error 越界

    println("====================")

    //（2）数组赋值
    //（2.1）修改某个元素的值
    arr(0) = 12
    arr(4) = 23
    println(arr(0)) //12
    println(arr(4)) //23

    //（2.2）采用方法的形式给数组赋值
    arr.update(0, 234)
    println(arr(0)) //234

    println("====================")

    // 3. 数组的遍历
    //（3.1）查看数组
    println(arr) //[I@3ac3fd8b
    println(arr.mkString(",")) //234,0,0,0,23

    println("====================")

    // 1) 普通for循环
    for (i <- 0 until arr.length) {
      /*
      12
      0
      0
      0
      23
       */
      println(arr(i))
    }
    println("====================")
    // 上面的便利可以简化如下
    /*
    12
    0
    0
    0
    23
     */
    for (i <- arr.indices) println(arr(i))
    println("====================")
    // 2) 直接遍历所有元素，增强for循环
    /*
    12
    0
    0
    0
    23
     */
    for (elem <- arr) println(elem)
    println("====================")
    // 3) 迭代器
    val iter = arr.iterator
    while (iter.hasNext) {

      /*
      234
      0
      0
      0
      23
       */
      println(iter.next())
    }

    println("====================")

    // 4) 调用foreach方法
    /*
    234
    0
    0
    0
    23
     */
    arr.foreach((elem: Int) => println(elem))

    println("====================")

    // 上面也可简写为
    arr.foreach(println)

    println("====================")
    //（4）增加元素（由于创建的是不可变数组，增加元素，其实是产生新的数组
    val newArr = arr.:+(11)
    println(newArr.mkString(",")) //234,0,0,0,23,11
    println(newArr.mkString("--")) //234--0--0--0--23--11

    // 上面添加元素也可简写如下
    val newArr2 = newArr.+:(22)
    println(newArr2.mkString(",")) //22,234,0,0,0,23,11

    // 上面的方法也可以继续简写如下(注意+的位置,是把要紧跟着新添加的元素,在前面代表添加在前面,在后面代表添加在后面)
    val newArr3 = newArr2 :+ 33
    val newArr4 = 44 +: 55 +: newArr3 :+ 66 :+ 77

    println(newArr3.mkString(",")) //22,234,0,0,0,23,11,33
    println(newArr4.mkString(",")) //44,55,22,234,0,0,0,23,11,33,66,77

    println("====================")
    // 另一种创建方式
    val arr2 = Array(11, 22, 33, 44, 55)
    arr2.foreach(println)
    /*
    11
    22
    33
    44
    55
     */

    val arr3 = Array.apply(1, 2, 3, 4, 5)
    arr3.foreach(println)
    /*
    1
    2
    3
    4
    5
     */

  }

}
