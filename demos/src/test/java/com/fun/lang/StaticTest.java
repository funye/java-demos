package com.fun.lang;

import org.junit.Test;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by fun on 2017/2/13.
 */
public class StaticTest {

    @Test
    public void test1(){
        StaticDemo.sayHello();
        StaticDemo.sayHello();
        StaticDemo sd = new StaticDemo();
        sd.sayHello2();
    }

    @Test
    public void test2(){
        StaticDemo.sayHello();
        StaticDemo sd = new StaticDemo();
        StaticDemo.sayHello();
        sd.sayHello2();
    }

    @Test
    public void testDate() throws ParseException {
        System.out.println( new java.util.Date().getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2017-03-04 09:00:00");
        System.out.println(date.getTime());
    }

    @Test
    public void testStringSplit() {
        String a = "a,b,c,,";
        String[] arr = a.split(",");
        System.out.println(arr.length);
    }

    @Test
    public void testBitoperator() {
        long size = 100000000;

        long time2 = System.currentTimeMillis();
        for (int j = 0; j < size; j++ ){
            int b = 100 >> 2;
        }
        long time3 = System.currentTimeMillis();
        System.out.println("=====>位运算除以2，耗时："+ (time3-time2) + "ms");

        long time0 = System.currentTimeMillis();
        for (int i = 0; i < size; i++ ){
            int a = 100 / 2;
        }
        long time1 = System.currentTimeMillis();
        System.out.println("=====>普通除以2，耗时："+ (time1-time0) + "ms");


    }

    @Test
    public void testDoubleCeil() {

        double d = 3.02;
        System.out.println(Math.ceil(d));
        d = 3.00;
        System.out.println(Math.ceil(d));

    }

}
