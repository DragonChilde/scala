package com.scala.chapter09

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-15 11:55
 * */
object Test03_Generics {

  def main(args: Array[String]): Unit = {
    // 1. 协变和逆变
    val child: Parent = new Child

    // error
    //val childList:MyCollection[Parent] = new MyCollection[Child]

    val childList: MyCollection[SubChild] = new MyCollection[Child]


    // 2. 上下限
    def test[A <: Child](a: A): Unit = {
      println(a.getClass.getName)
    }

    test[SubChild](new SubChild) //com.scala.chapter09.SubChild


  }

}


// 定义继承关系
class Parent {}

class Child extends Parent {}

class SubChild extends Child {}

// 定义带泛型的集合类型
class MyCollection[-E] {}
