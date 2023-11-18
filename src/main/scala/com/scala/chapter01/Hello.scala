package com.scala.chapter01

/**
 * main 方法名
 * 小括号表示参数列表
 * 参数声明方式: java -> 类型 参数名
 * scala -> 参数名: 类型
 * public修饰符:scala 中没有public关键字,如果不声明访问权限,那就是公共的
 * static修饰符: scala 中没有静态语法,所以没有static关键字
 * void关键字: 表示返回值,但是不遵循面向对象语法,所以scala中没有,但是有Unit类型,表示没有返回值
 * scala中: 方法名(参数列表):返回值此类型
 * scala中声明方法必须采用关键字def声晚
 * scala中方法实现赋值给方法声明,所以中间需要等号连接
 *
 * Scala是一个完全面向对象的语言,所以没有静态语法,为了能调用静态语法(模仿静态语法),采用伴生对象单例的方式调用方法
 */

/**
 *  object: 关键字，声明一个单例对象（伴生对象）
 *  可以理解为替代Java的static关键字的方式，将静态方法用单例对象的实例方法做了替代，做到了更纯粹的面向对象。
 */
object Hello {

  def main(args: Array[String]): Unit = {
    println("hello scala")
  }
}
