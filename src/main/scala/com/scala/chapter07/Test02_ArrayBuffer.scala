package com.scala.chapter07

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object Test02_ArrayBuffer {

  def main(args: Array[String]): Unit = {
    // 1. 创建可变数组
    val arr1: ArrayBuffer[Int] = new ArrayBuffer[Int]()

    // 创建并初始赋值可变数组
    val arr2 = ArrayBuffer(11, 22, 33)

    println(arr1) //ArrayBuffer()
    println(arr2) //ArrayBuffer(11, 22, 33)

    println("==================================")
    // 2. 访问元素
    println(arr2(0)) //11
    arr2(0) = 44
    println(arr2(0)) //44

    println("==================================")

    // 3. 添加元素
    // 尾部添加元素
    val newArr1 = arr2 :+ 15
    println(arr2) //ArrayBuffer(44, 22, 33)
    println(newArr1) //ArrayBuffer(44, 22, 33, 15)
    println(arr2 == newArr1) //false

    val newArr2 = arr2 += 19
    println(arr2) //ArrayBuffer(44, 22, 33, 19)
    println(newArr2) //ArrayBuffer(44, 22, 33, 19)
    println(arr2 == newArr2) //true

    newArr2 += 13
    println(arr2) //ArrayBuffer(44, 22, 33, 19, 13)

    println("==================================")

    // 头部添加元素
    77 +=: arr2
    println(arr2) //ArrayBuffer(77, 44, 22, 33, 19, 13)
    println(newArr2) //ArrayBuffer(77, 44, 22, 33, 19, 13)

    arr2.append(36)
    // 此方法存在冲突,建议使用appendAll,如下
    //arr1.prepend(11, 76)
    arr2.appendAll(ArrayBuffer(11, 22, 33)) //ArrayBuffer(77, 44, 22, 33, 19, 13, 36, 11, 22, 33)
    println(arr2)

    // 向指定的位置插入数据,在0位插入值为1的元素
    arr2.insert(0, 1)
    println(arr2) //ArrayBuffer(1, 77, 44, 22, 33, 19, 13, 36, 11, 22, 33)
    // 指定的位置插入数组
    arr2.insertAll(1, ArrayBuffer(2, 3))
    println(arr2) //ArrayBuffer(1, 2, 3, 77, 44, 22, 33, 19, 13, 36, 11, 22, 33)

    println("==================================")

    // 4. 删除元素
    arr2.remove(3)
    println(arr2) //ArrayBuffer(1, 2, 3, 44, 22, 33, 19, 13, 36, 11, 22, 33)

    // 参数一,起始位,参数二,删除个数
    arr2.remove(0, 5)
    println(arr2) //ArrayBuffer(33, 19, 13, 36, 11, 22, 33)

    // 删除值为33的元素(重复元素只能删除一个)
    arr2 -= 33
    println(arr2) //ArrayBuffer(19, 13, 36, 11, 22, 33)

    println("==================================")

    // 5. 可变数组转换为不可变数组
    val arr: ArrayBuffer[Int] = ArrayBuffer(22, 33, 44)
    val newArr: Array[Int] = arr.toArray
    println(newArr.mkString(",")) //22,33,44

    println("==================================")

    // 6. 不可变数组转换为可变数组
    val buffer: mutable.Buffer[Int] = newArr.toBuffer
    println(buffer) //ArrayBuffer(22, 33, 44)
    println(newArr.mkString(",")) //22,33,44

  }

}
