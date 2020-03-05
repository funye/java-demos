package com.fun.lang;

/**
 * Created by yehuan on 2017/2/13.
 */
public class StaticDemo {
    private static String prop = null;
    static{
        prop = "static";
        System.out.println("static code block.......");
    }
    public StaticDemo(){
        prop = "constructor";
        System.out.println("constructor.......");
    }

    public static void sayHello(){

        System.out.println("sayHello method print the prop="+prop);
    }
    public void sayHello2(){
        prop = "sayHello2";
        System.out.println("sayHello2 method print the prop="+prop);
    }
}
