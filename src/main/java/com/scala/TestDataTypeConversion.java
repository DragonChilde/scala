package com.scala;

public class TestDataTypeConversion {

    public static void main(String[] args) {
        byte b = 10;
        test(b);        //aaaa
        /*
            当test(byte b)注释后,输出的是bbbb
            当test(short s)注释后,输出的是dddd
            虽然char也是2个字节,但与其它几个类型是不同类型,所以只会提升到相同类型
         */
        char c = 'a';
        short c2 = (short) c;
        test(c2);       //bbbb
        test(c);        //cccc

    }
/*

    public static void test(byte b) {
        System.out.println("aaaa");
    }
*/

/*    public static void test(short s) {
        System.out.println("bbbb");
    }*/

    public static void test(char c) {
        System.out.println("cccc");
    }

    public static void test(int i) {
        System.out.println("dddd");
    }
}
