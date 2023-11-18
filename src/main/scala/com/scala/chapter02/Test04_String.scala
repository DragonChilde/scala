package com.scala.chapter02

object Test04_String {
  def main(args: Array[String]): Unit = {

    //1. 字符串,通过+号连接
    val name: String = "jack"
    val age: Int = 18

    println(age + " old " + name + " learning in school")

    //用于将一个字符串复制多次并拼接
    println(name * 3) //jackjackjack

    //2. printf用法：字符串，通过%传值
    printf("%d old %s learning in school", age, name)

    println()
    // 3.字符串模板（插值字符串）：通过$获取变量值
    println(s"${age} old ${name} learning in school")


    val num: Double = 1.2345
    println(f"The num is ${num}%2.2f") // 格式化模板字符串 The num is 1.23
    println(raw"The num is${num}%2.2f") // 不作格式化直接输出  The num is1.2345%2.2f

    // 三引号表示字符串，保持多行字符串的原格式输出
    val sql =
      s"""|select *
          |from
          | student
          |where
          | name = ${name}
          |and
          | age > ${age}
          |""".stripMargin
          /*
          select *
          from
           student
          where
           name = jack
          and
           age > 18
           */
    println(sql)
  }

}
