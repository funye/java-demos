package com.fun.lang;

/**
 *
 * Created by yehuan on 2017/2/13.
 */
public class InnerClassDemo {

    private String name;

    class InnerOne {
        private int a;

        class InnerTwo{
            private int b;
        }
    }

    public static void main(String[] args) {
        new InnerClassDemo();
        System.out.println("aaaaa");
    }
}
