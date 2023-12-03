package com.scala.chapter05

object Test03_FunctionParameter {

  def main(args: Array[String]): Unit = {
    // 1. 可变参数
    def f1(str: String*): Unit = {
      println(str)
    }

    f1("jack") //ArraySeq(jack)
    f1("aaa", "bbb", "ccc") //ArraySeq(aaa, bbb, ccc)
    println("==============================")


    // 2. 如果参数列表中存在多个参数，那么可变参数一般放置在最后
    def f2(str1: String, str2: String*): Unit = {
      println("str1:" + str1 + " str2:" + str2)
    }

    f2("tom") //str1:tom str2:List()
    f2("aaa", "bbb", "ccc") //str1:aaa str2:ArraySeq(bbb, ccc)

    println("==============================")


    //3 . 数默认值，一般将有默认值的参数放置在参数列表的后面
    def f3(name: String = "sanshang"): Unit = {
      println("My name is " + name)
    }

    f3("julia") //My name is julia
    f3() //My name is sanshang

    println("==============================")

    //4. 带名参数
    def f4(name: String = "sanshang", age: Int): Unit = {
      println(s"${age} old ${name} learning at school")
    }

    f4("marry", 18) //18 old marry learning at school
    f4(age = 11, name = "karry") //11 old karry learning at school
    f4(age = 19) //19 old sanshang learning at school

  }

}
