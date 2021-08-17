package com.fun.simple;

public class Temp {

    public static void main(String[] args) {
        Long count = 1L;
        count = count == null ? 1 : count++;
        System.out.println(count);
    }
}
