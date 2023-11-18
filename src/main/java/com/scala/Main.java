package com.scala;


import java.util.*;


/**
 * @program: ${PROJECT_NAME}
 * @description: ${description}
 * @author: JunWen
 * @create: ${YEAR}-${MONTH}-${DAY} ${HOUR}:${MINUTE}
 **/
public class Main {


    private static final HashMap<Integer, String> ballColor = new HashMap<>();
    private static final TreeMap<Integer, String> result = new TreeMap<>();

    private static final Random random = new Random();

    static {
        ballColor.put(1, "red");
        ballColor.put(2, "blue");
        ballColor.put(3, "green");
    }

    public static void main(String[] args) {


        for (int i = result.size(); i < 2; ) {
            int num = random.nextInt(1, 4);
            if (result.get(num) == null && ballColor.containsKey(num)) {
                result.put(num, ballColor.get(num));
                i++;
            }
        }


        System.out.println(result);

    }
}