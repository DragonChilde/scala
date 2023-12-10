package com.scala;

/**
 * @program: scala
 * @description: dynamic bind
 * @author: JunWen
 * @create: 2023-12-06 16:00
 **/
public class TestDynamicBind {

    public static void main(String[] args) {

        Worker worker = new Worker();
        System.out.println(worker.name);    //worker
        worker.hello(); //hello worker
        worker.hi();    //hi worker

        System.out.println("==============================");

        // 多态
        Person person = new Worker();
        System.out.println(person.name);    //person    // 静态绑定属性
        person.hello(); //hello worker  // 动态绑定方法
        ///person.hi();     //error
    }
}

class Person {
    String name = "person";

    public void hello() {
        System.out.println("hello " + this.name);
    }
}

class Worker extends Person {
    String name = "worker";

    public void hello() {
        System.out.println("hello " + this.name);
    }

    public void hi() {
        System.out.println("hi " + this.name);
    }
}
