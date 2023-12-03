package com.scala.chapter05

import scala.collection.mutable.ArrayBuffer


object Test07_Practice_CollectionOperation {

  def main(args: Array[String]): Unit = {

    val arr: Array[Int] = Array(11, 22, 33, 44, 55, 66)

    // 对数组进行处理，将操作抽象出来，处理完毕之后的结果返回一个新的数组
    def arrayOperation(array: Array[Int], op: Int => Int): Array[Int] = {
      for (elem <- array) yield op(elem)
    }

    // 定义一个加一操作
    def addOne(elem: Int): Int = {
      elem + 1
    }

    //调用函数
    val newArray: Array[Int] = arrayOperation(arr, addOne)
    println(newArray.mkString(",")) //12,23,34,45,56,67

    println("---------------------")

    // 传入匿名函数，实现元素翻倍
    val newArray2 = arrayOperation(arr, _ * 2)
    println(newArray2.mkString(",")) //22,44,66,88,110,132

    println("===================")

    //1 .Map映射
    def map(arr: Array[Int], op: Int => Int) = {
      for (elem <- arr) yield op(elem)
    }

    val mapArr = map(Array(1, 2, 3, 4, 5), (x: Int) => {
      x * x
    })

    println(mapArr.mkString(","))
    println("===================")

    // 2. filter 过滤。有参数，且参数再后面只使用一次，则参数省略且 后面参数用_表示
    def filter(arr: Array[Int], op: Int => Boolean) = {
      var arr1: ArrayBuffer[Int] = ArrayBuffer[Int]()
      for (elem <- arr if op(elem)) {
        arr1.append(elem)
      }
      arr1.toArray
    }

    var arr1 = filter(Array(1, 2, 3, 4), _ % 2 == 1)
    println(arr1.mkString(",")) //1,3

    println("======================")

    //3. reduce 聚合。有多个参数，且每个参数再后面只使用一次，则参数省略且后面参数用_表示，第 n 个_代表第 n 个参数
    def reduce(arr: Array[Int], op: (Int, Int) => Int) = {
      var init: Int = arr(0)
      for (elem <- 1 to arr.length) {
        init = op(init, elem)
      }
      init
    }

    val arr2 = reduce(Array(1, 2, 3, 4), (x, y) => x * y)
    println(arr2) //24
    val arr3 = reduce(Array(1, 2, 3, 4), _ * _)
    println(arr3) //24
  }





}
