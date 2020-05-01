package com.fun.thread;

/**
 * Created by huanye on 2017/6/6.
 */
public class ThreadTestDemo extends Thread {
    @Override
    public void run() {
        System.out.println("---------before--------");
        super.run();
        System.out.println("---------after--------");

    }

    public static void main(String[] args) {

        ThreadTestDemo t = new ThreadTestDemo();
        t.start();
        System.out.println("-----main------");

    }
}
