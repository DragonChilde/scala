package com.scala.chapter07

object Test03_MulArray {

  def main(args: Array[String]): Unit = {
    // 1. 创建二维数组(第一个参数是有几维,第二个参数是有几个元素)
    val array: Array[Array[Int]] = Array.ofDim[Int](2, 3)

    // 2. 访问元素
    array(0)(2) = 11
    array(1)(0) = 22


    println(array.mkString(",")) //[I@2c6a3f77,[I@246ae04d
    //遍历二维数组
    for (i <- 0 until array.length; j <- 0 until array(i).length) { //i 就是一维数组
      /*
      0
      0
      11
      22
      0
      0
       */
      println(array(i)(j))
    }

    println("=====================")

    for (i <- array.indices; j <- array(i).indices) {
      /*
      0	0	11
      22	0	0
       */
      print(array(i)(j) + "\t")
      if (j == array(i).length - 1) println()
    }


    println("=====================")

    //使用foreach遍历
    array.foreach(line => line.foreach(println))
    /*
      0
      0
      11
      22
      0
      0
       */
    println("=====================")

    // 上面方法也可简写如下
    array.foreach(_.foreach(println))
    /*
    0
    0
    11
    22
    0
    0
     */

  }

}
