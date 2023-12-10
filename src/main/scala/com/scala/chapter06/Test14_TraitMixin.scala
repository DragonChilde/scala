package com.scala.chapter06

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-07 21:58
 * */
object Test14_TraitMixin {

  def main(args: Array[String]): Unit = {

    val student = new Student14
    student.study()
    student.increase()
    student.play()
    student.increase()

    /*
    student student is studying
    student student knowledge increased: 1
    young people student is playing
    student student knowledge increased: 2
     */

    println("===========================")

    //（4）动态混入：可灵活的扩展类的功能
    val studentWithTalent = new Student14 with Talent {
      override def singing(): Unit = println("student is good at singign")

      override def dancing(): Unit = println("student is good at dancing")
    }

    studentWithTalent.sayHello()
    studentWithTalent.play()
    studentWithTalent.study()
    studentWithTalent.dating()
    studentWithTalent.dating()
    studentWithTalent.singing()

    /*
    hello from: student
    hello from : student student
    young people student is playing
    student student is studying
    student student is dating
    student student is dating
    student is good at singign
     */

  }

}

// 再定义一个特质
trait Knowledge {
  var amount: Int = 0

  def increase(): Unit
}

trait Talent {
  def singing(): Unit

  def dancing(): Unit
}

class Student14 extends Person13 with Young with Knowledge {

  // 重写冲突的属性
  override val name: String = "student"

  // 实现抽象方法
  override def dating(): Unit = println(s"student $name is dating")

  def study(): Unit = println(s"student $name is studying")

  // 重写父类方法
  override def sayHello(): Unit = {
    super.sayHello()
    println(s"hello from : student $name")

  }

  // 实现特质中的抽象方法
  override def increase(): Unit = {
    amount += 1
    println(s"student $name knowledge increased: $amount")
  }
}