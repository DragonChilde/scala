package com.scala.chapter06

/**
 * @program: scala
 * @description: ${description}
 * @author: JunWen
 * @create: 2023-12-08 10:02
 * */
object Test16_TraitSelfType {

  def main(args: Array[String]): Unit = {

    val user = new RegisterUser("alice", "123456")
    user.insert() //insert into db:alice
  }

}

class User(val name: String, val password: String)

trait UserDao {

  _: User =>

  def insert(): Unit = {
    println(s"insert into db:${this.name}")
  }

}

class RegisterUser(name: String, password: String) extends User(name, password) with UserDao