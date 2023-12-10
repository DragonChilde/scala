package com.scala.chapter06

import scala.beans.BeanProperty

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-05 09:32
 * */
object Test03_Class {
  def main(args: Array[String]): Unit = {
    // 创建一个对象
    val animal = new Bird
    println(animal.age) //0
    println(animal.sex) //null

    animal.sex = "female"
    println(animal.sex) //female

  }

}

//1. Scala 语法中，类并不声明为 public，所有这些类都具有公有可见性（即默 认就是 public）
class Animal {

}

// 定义一个类
// 2. 一个 Scala 源文件可以包含多个类
class Bird {

  // 定义属性
  private var name: String = "angryBird"

  //Bean 属性（@BeanProperty）
  @BeanProperty
  var age: Int = _   //_表示给属性一个默认值
  //val 修饰的属性不能赋默认值，必须显示指定

  var sex: String = _

}