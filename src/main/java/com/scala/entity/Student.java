package com.scala.entity;

public class Student {

    private String name;
    private Integer age;
    private static String school = "scala";

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void printInfo() {
        System.out.println(this.name + " " + this.age + " " + Student.school);
    }


    public static void main(String[] args) {
        Student stu = new Student("cang", 19);
        stu.printInfo();


    }
}
