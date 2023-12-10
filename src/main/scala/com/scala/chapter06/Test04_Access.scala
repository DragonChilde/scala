package com.scala.chapter06

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-05 09:51
 * */
object Test04_Access {

  def main(args: Array[String]): Unit = {

    // 创建对象
    val person: Person = new Person
    //person.idCard //error
    //person.name //error
    println(person.age) //20
    println(person.sex) //female


    person.printInfo() //Person: 12345567 terry female 20

    println("=================")

    var worker: Worker = new Worker

    worker.age = 11
    worker.printInfo() //Worker: bob 22 male


  }

}

// 定义一个子类
class Worker extends Person {
  override def printInfo(): Unit = {

    //println(idCard) //error
    name = "bob"
    age = 22
    sex = "male"
    println(s"Worker: $name $age $sex")

  }
}
