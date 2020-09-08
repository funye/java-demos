package com.fun.algorithm;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class LuckyBall {

    public static void main(String[] args) {
        LuckyBall lb = new LuckyBall();
        System.out.println("机选一注大乐透：");
        System.out.println(lb.randomOneBigHappy());
        System.out.println("-------------------");
        System.out.println("机选五注大乐透：");
        System.out.println(lb.randomFiveBigHappy());
        System.out.println("-------------------");
        System.out.println("机选一注双色球：");
        System.out.println(lb.randomOneDoubleColor());
        System.out.println("-------------------");
        System.out.println("机选五注双色球：");
        System.out.println(lb.randomFiveDoubleColor());
    }

    public String randomOneBigHappy() {
        return bigHappy();
    }

    public String randomFiveBigHappy() {
        String result = "";
        for (int i = 0; i < 5; i++) {
            result += bigHappy() + "\n";
        }
        return result;
    }

    public String randomOneDoubleColor() {
        return doubleColor();
    }

    public String randomFiveDoubleColor() {
        String result = "";
        for (int i = 0; i < 5; i++) {
            result += doubleColor() + "\n";
        }
        return result;
    }


    private int randomInt(int min, int max) {
        return (int) Math.floor(Math.random() * (max-min)) + min;
    }

    private int randomInt(int bound) {
        return randomInt(0, bound);
    }



    /**
     * 1-33 --> 6
     * 1-16 --> 1
     */
    private String doubleColor() {
        int RED_SIZE = 33;
        int BLUE_SIZE = 16;
        List<Ball> red = new ArrayList<>(RED_SIZE);
        // init pool
        for (int i = 0; i < RED_SIZE; i++) {
            red.add(Ball.builder()
                    .number(i + 1)
                    .color(Color.RED)
                    .weight(0) // 默认权重
                    .build());
        }
        List<Ball> blue = new ArrayList<>(BLUE_SIZE);
        for (int i = 0; i < BLUE_SIZE; i++) {
            blue.add(Ball.builder()
                    .number(i + 1)
                    .color(Color.BLUE)
                    .weight(0) // 默认权重
                    .build());
        }

        // random 6 red ball and 1 blue ball
        List<Integer> result = new ArrayList(6);
        int redBound = RED_SIZE;
        for (int i = 0; i < 6; i++) {
            int index = randomInt(redBound);
            result.add(red.get(index).getNumber());
            red.remove(index);
            --redBound;
        }
        result.sort((o1, o2) -> o1.compareTo(o2));


        // 拼接
        String rs = "";
        for (Integer i : result) {
            rs += i + " ";
        }
        int blueIdx = randomInt(BLUE_SIZE);
        rs += "| " + blue.get(blueIdx).getNumber() + " ";
        blue.remove(blueIdx);

        return rs;
    }


    /**
     * 1-35 --> 5
     * 1-12 --> 2
     */
    private String bigHappy() {
        List<Ball> red = new ArrayList<>(35);
        // init pool
        for (int i = 0; i < 35; i++) {
            red.add(Ball.builder()
                    .number(i + 1)
                    .color(Color.RED)
                    .weight(0) // 默认权重
                    .build());
        }
        List<Ball> blue = new ArrayList<>(12);
        for (int i = 0; i < 12; i++) {
            blue.add(Ball.builder()
                    .number(i + 1)
                    .color(Color.BLUE)
                    .weight(0) // 默认权重
                    .build());
        }

        // random 5 red ball and 2 blue ball
        List<Integer> result = new ArrayList(5);
        int redBound = 35;
        for (int i = 0; i < 5; i++) {
            int index = randomInt(redBound);
            result.add(red.get(index).getNumber());
            red.remove(index);
            --redBound;
        }

        result.sort((o1, o2) -> o1.compareTo(o2));


        int blueIdx = randomInt(12);
        int blueOne = blue.get(blueIdx).getNumber();
        blue.remove(blueIdx);
        blueIdx = randomInt(11);
        int blueTwo = blue.get(blueIdx).getNumber();
        blue.remove(blueIdx);

        String rs = "";
        for (Integer i : result) {
            rs += i + " ";
        }
        rs += "| ";
        if (blueOne <= blueTwo) {
            rs += blueOne + " " + blueTwo;
        } else {
            rs += blueTwo + " " + blueOne;
        }

        return rs;
    }


}

enum Color {
    RED, BLUE;
}

@Builder
@Getter
@Setter
class Ball {
    int number;
    int weight;
    Color color;
}

