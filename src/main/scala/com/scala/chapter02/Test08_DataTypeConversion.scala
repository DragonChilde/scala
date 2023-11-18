package com.scala.chapter02

object Test08_DataTypeConversion {

  def main(args: Array[String]): Unit = {
    // 1. 自动类型转换
    //    （1）自动提升原则：有多种类型的数据混合运算时，系统首先自动将所有数据转换成精度大的那种数据类型，然后再进行计算。
    var n = 1 + 2.0
    println(n) //n 就是Double 3.0

    //    （2）把精度大的数值类型赋值给精度小的数值类型时，就会报错，反之就会进行自动类型转换。
    val a2: Byte = 10
    val b2: Int = a2
    //val c2: Byte = b2   //  error

    //    （3）（byte，short）和char之间不会相互自动转换。
    val a3: Byte = 10
    val b3: Char = 'b'
    //val c3: Byte = b3   // 执行时报异常 error
    val c3: Int = b3
    println(c3) //98

    //    （4）byte，short，char他们三者可以计算，在计算时首先转换为int类型。
    val a4: Byte = 12
    val b4: Short = 25
    val c4: Char = 'c'
    val result4: Int = a4 + b4
    val result44: Int = a4 + b4 + c4
    println(result44) //136

    // 2. 强制类型转换
    //    （1）将数据由高精度转换为低精度，就需要使用到强制转换
    val n1: Int = -2.9.toInt
    println("n1:" + n1) //n1:-2   存在精度 损失

    //    （2）强转符号只针对于最近的操作数有效，往往会使用小括号提升优先级
    val n2: Int = 2.6.toInt + 3.7.toInt
    val n3: Int = (2.6 + 3.7).toInt
    println("n2 : " + n2) //n2 : 5
    println("n3 : " + n3) //n3 : 6

    // 3. 数值类型和String类型的转换
    // (1) 数值转String
    val n4: Int = 27
    val s: String = n4 + ""
    println(s) //27


    // (2) String转数值
    val m: Int = "12".toInt
    val f: Float = "12.3".toFloat
    val f2: Int = "12.3".toDouble.toInt
    println(f2) //12


  }

}
