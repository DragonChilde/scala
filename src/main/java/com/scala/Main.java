package com.scala;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @program: ${PROJECT_NAME}
 * @description: ${description}
 * @author: JunWen
 * @create: ${YEAR}-${MONTH}-${DAY} ${HOUR}:${MINUTE}
 **/
public class Main {


    private static final HashMap<Integer, String> ballColor = new HashMap<>();
    private static final TreeMap<Integer, String> result = new TreeMap<>();

    private static final TreeMap<Integer, Integer> countRes = new TreeMap<>();

    private static final Map<Integer, String> numMap = new HashMap<>();

    private static final Random random = ThreadLocalRandom.current();

    static {
        ballColor.put(1, "red");
        ballColor.put(2, "blue");
        ballColor.put(3, "green");

        countRes.put(1, 0);
        countRes.put(2, 0);
        countRes.put(3, 0);


        numMap.put(1, "red");
        numMap.put(2, "red");
        numMap.put(3, "blue");
        numMap.put(4, "blue");
        numMap.put(5, "green");
        numMap.put(6, "green");
        numMap.put(7, "red");
        numMap.put(8, "red");
        numMap.put(9, "blue");
        numMap.put(10, "blue");
        numMap.put(11, "green");
        numMap.put(12, "red");
        numMap.put(13, "red");
        numMap.put(14, "blue");
        numMap.put(15, "blue");
        numMap.put(16, "green");
        numMap.put(17, "green");
        numMap.put(18, "red");
        numMap.put(19, "red");
        numMap.put(20, "blue");
        numMap.put(21, "green");
        numMap.put(22, "green");
        numMap.put(23, "red");
        numMap.put(24, "red");
        numMap.put(25, "blue");
        numMap.put(26, "blue");
        numMap.put(27, "green");
        numMap.put(28, "green");
        numMap.put(29, "red");
        numMap.put(30, "red");
        numMap.put(31, "blue");
        numMap.put(32, "green");
        numMap.put(33, "green");
        numMap.put(34, "red");
        numMap.put(35, "red");
        numMap.put(36, "blue");
        numMap.put(37, "blue");
        numMap.put(38, "green");
        numMap.put(39, "green");
        numMap.put(40, "red");
        numMap.put(41, "blue");
        numMap.put(42, "blue");
        numMap.put(43, "green");
        numMap.put(44, "green");
        numMap.put(45, "red");
        numMap.put(46, "red");
        numMap.put(47, "blue");
        numMap.put(48, "blue");
        numMap.put(49, "green");


    }

    public static void main(String[] args) {


/*        for (int i = result.size(); i < 2; ) {
            int num = random.nextInt(3) + 1;
            if (result.get(num) == null && ballColor.containsKey(num)) {
                result.put(num, ballColor.get(num));
                i++;
            }
        }


        System.out.println(result);*/


        for (int i = 0; i < 100; i++) {
            for (int j = result.size(); j < 2; ) {
                int num = random.nextInt(3) + 1;
                if (result.get(num) == null && ballColor.containsKey(num)) {
                    result.put(num, ballColor.get(num));
                    Integer count = countRes.get(num);
                    countRes.put(num, count + 1);
                    j++;
                }
            }

            result.clear();
        }


        System.out.println(countRes);

        int num = random.nextInt(48) + 1;

        System.out.println(num + " : " + numMap.get(num));

    }
}