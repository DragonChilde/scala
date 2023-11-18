package com.scala;

public class TestOperator {

    public static void main(String[] args) {
        // 比较运算符
        String s1 = "Hello";
        String s2 = new String("Hello");


        Boolean isEqual = s1 == s2;
        System.out.println(isEqual);    // false
        System.out.println(s1.equals(s2));  //true

    }
}
