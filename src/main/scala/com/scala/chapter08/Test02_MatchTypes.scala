package com.scala.chapter08

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-15 11:34
 * */
object Test02_MatchTypes {

  def main(args: Array[String]): Unit = {
    // 1. 匹配常量
    def describeConst(x: Any): String = x match {
      case 1 => "Int one"
      case "hello" => "String hello"
      case true => "Boolean true"
      case '+' => "Char +"
      case _ => ""
    }

    println(describeConst("hello")) //String hello
    println(describeConst('+')) //Char +
    println(describeConst(0.3))


    println("==================================")


    // 2. 匹配类型
    def describeType(x: Any): String = x match {
      case i: Int => "Int " + i
      case s: String => "String " + s
      case list: List[String] => "List " + list
      case array: Array[Int] => "Array[Int] " + array.mkString(",")
      case a => "Something else: " + a
    }

    println(describeType(22)) //Int 22
    println(describeType("hello")) //String hello
    println(describeType(List("hi", "hello"))) //List List(hi, hello)
    // List 泛型擦除
    println(describeType(List(10, 20))) //List List(10, 20)
    ////数组例外，可保留泛型
    println(describeType(Array("java", "scala"))) //Something else: [Ljava.lang.String;@2ef9b8bc
    println(describeType(Array(11, 22))) //Array[Int] 11,22


    println("==================================")

    // 3. 匹配数组
    for (arr <- List(
      Array(0),
      Array(1, 0),
      Array(0, 1, 0),
      Array(1, 1, 0),
      Array(2, 3, 7, 15),
      Array("hello", 1, 30),
    )) {
      val result = arr match {
        case Array(0) => "0" //匹配 Array(0) 这个数组
        case Array(1, 0) => "Array(1,0)"
        case Array(x, y) => "Array: " + x + " , " + y //匹配有两个元素的数组，然后将将元素值赋给对应的 x, y
        case Array(0, _*) => "以0开头的数组"
        case Array(x, 1, z) => "中间为1的三元素数组"
        case _ => "something else"

      }

      /*
      0
      Array(1,0)
      以0开头的数组
      中间为1的三元素数组
      something else
      中间为1的三元素数组
       */
      println(result)
    }

    println("==================================")

    // 4. 匹配列表
    // 方式一
    for (list <- List(
      List(0),
      List(1, 0),
      List(0, 0, 0),
      List(1, 1, 0),
      List(11),
      List("hello")
    )) {
      val result = list match {
        case List(0) => "0" //匹配 List(0)
        case List(x, y) => "List(x,y): " + x + " , " + y //匹配有两个元素的 List
        case List(0, _*) => "List(0,....)"
        case List(a) => "List(a): " + a
        case _ => "something else"
      }

      /*
      0
      List(x,y): 1 , 0
      List(0,....)
      something else
      List(a): 11
      List(a): hello
       */
      println(result)
    }

    println("----------------------")

    // 方式二
    val list = List(11, 22, 33, 44, 55) //first : 11 , second: 22, rest : List(33, 44, 55)
    val list2 = List(10) //something else

    list match {
      case first :: second :: rest => println(s"first : $first , second: $second, rest : $rest")
      case _ => println("something else")
    }

    println("==================================")



    // 5. 匹配元组
    //对一个元组集合进行遍历
    for (tuple <- List(
      (0, 1),
      (0, 0),
      (0, 1, 0),
      (0, 1, 1),
      (1, 23, 56),
      ("hello", true, 0.5)
    )) {
      val result = tuple match {
        case (a, b) => "" + a + " , " + b
        case (0, _) => "(0,_)"   //是第一个元素是 0 的元组
        case (a, 1, _) => "(a,1,_) " + a
        case (x, y, z) => "(x,y,z) " + x + " " + y + " " + z
        case _ => "something else"  //默认
      }

      /*
      0 , 1
      0 , 0
      (a,1,_)0
      (a,1,_)0
      (x,y,z)1 23 56
      (x,y,z)hello true 0.5
       */
      println(result)
    }
  }

}
