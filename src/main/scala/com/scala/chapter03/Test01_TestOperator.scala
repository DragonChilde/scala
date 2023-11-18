package com.scala.chapter03

object Test01_TestOperator {

  def main(args: Array[String]): Unit = {

    // 1. 算术运算符
    //对于除号“/”，它的整数除和小数除是有区别的：整数之间做除法时，只保留整数部分而舍弃小数部分
    val result: Int = 10 / 3
    println(result) //3

    val result2: Double = 10 / 3
    println(result2) //3.0

    val result3: Double = 10.0 / 3
    // 含义:保留小数点2位,使用四舍五入

    //println(result3.formatted("%5.2f")) //3.33 此方法在JDK15后已弃用,使用以后方式代替
    println("%5.2f".format(result3)) //3.33

    //对一个数取模 a%b，和 Java 的取模规则一样
    val result4: Int = 10 % 3
    println(result4) //1


    // 2. 比较运算符
    val s1: String = "hello"
    val s2: String = new String("hello")

    println(s1 == s2) //true
    println(s1.equals(s2)) //true
    println(s1.eq(s2)) //false

  }

}
