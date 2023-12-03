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

    //3. 逻辑运算符
    def m(n: Int): Int = {
      println("m被调用")
      return n
    }

    val n = 1
    println((4 > 5) && m(n) > 0) //false

    // 判断一个字符串是否为空
    //扩展避免逻辑与空指针异常
    def isNotEmpty(str: String): Boolean = {
      return str != null && !("".equals(str.trim))
    }

    println(isNotEmpty(null)) //false

    // 4. 赋值运算符
    /*
      var b: Byte = 10
      b += 1
      println(b)  //error 运行时报异常,会自动升格为int类型
      */

    var i: Int = 12
    i += 1
    println(i) //13

    // 5. 位运算符
    val a: Byte = 60
    println(a << 3) //0011 1100 => 1 1110 0000 =>480
    println(a >> 2) //0011 1100 => 0000 1111 =>15

    val b: Short = -13
    println(b << 2) //1000 1101 => 1111 0010 => 1111 0011 => 1100 1100 => 1100 1011 =>1011 0100 =>-52
    println(b >> 2) //1000 1101 => 1111 0010 => 1111 0011 => 0011 1100 => 0011 1011 =>1000 0100 =>-4
    println(b >>> 2) //1073741820


    // 6. 运算符的本质
    val n1: Int = 12
    val n2: Int = 37
    //1. 当调用对象的方法时，.可以省略
    println(n1.+(n2)) //49
    println(n1 + n2) //49

    println(1.34.*(25)) //33.5
    println(1.34 * 25) //33.5

    //2. 如果函数参数只有一个，或者没有参数，()可以省略
    //println(1.toString())
    //println(1 toString)
    //println(1.1 toInt toString)

  }

}
