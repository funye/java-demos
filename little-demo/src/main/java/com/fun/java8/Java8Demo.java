package com.fun.java8;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * java 8 new feature test demo
 * Created by huanye on 2017/6/7.
 */
public class Java8Demo {

    public static void main(String[] args) {
        Java8Demo demo = new Java8Demo();
//        demo.testLambda();
//        demo.testStream();
        demo.testDate();
    }

    /**
     * java 8 lambda expression test
     * how to use:
     * 1. (params) -> expression
     * 2. (params) -> statement
     * 3. (params) -> { statements }
     */
    public void testLambda() {
        // 1. use as anonymous inner class. such as Collection.sort .eg
        new Thread(()-> System.out.println("this used like an anonymous Runnable")).start();

        List<Integer> list = Arrays.asList(100,300,200,500,400);
        list.sort((o1,o2)->{if (o1>o2) return 1;else return -1;});// lambda expression just like an anonymous comparator
        System.out.println(list);

        // 2. use in collection loop search
        list.forEach(e-> System.out.print(e+" "));// normal loop
        list.forEach(System.out ::println);// method reference, can not pass parameters
    }

    /**
     * java 8 stream test
     */
    public void testStream() {

        // 1. use stream and lambda

        // 不使用lambda表达式为每个订单加上12%的税,然后得到总金额
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double sum = 0.0;
        for (Integer cost : costBeforeTax) {
            double price = cost + .12*cost;
            sum += price;
            System.out.println(price);
        }
        System.out.println("total money is: " + sum);

        // 使用lambda表达式
        List<Integer> costBeforeTax2 = Arrays.asList(100, 200, 300, 400, 500);
        double sum2 = costBeforeTax2.stream().map((cost) -> cost + .12*cost).reduce( 0.0,(a,b)->{System.out.println(b);return a+b;} );
        System.out.println("total money is (use lambda):" + sum2);


        // 2. use filter to select
        List<String> strList = Arrays.asList("hello","yes","no","world","java", "aaa", "kafka");
        List<String> filtered = strList.stream().filter(x -> x.length()> 3).map(String::toUpperCase).collect(Collectors.toList());
        System.out.printf("Original List : %s, filtered list : %s %n", strList, filtered);
        List<String> filtered2 = strList.parallelStream().filter(x -> x.length()> 3).map(String::toUpperCase).collect(Collectors.toList());
        System.out.printf("Original List : %s, filtered list : %s %n", strList, filtered2);

        // 3. use stream to calculate
        List<Integer> integerList = Arrays.asList(10,20,40,80,50);
        IntSummaryStatistics summaryStatistics = integerList.stream().mapToInt((x)->x).summaryStatistics();
        System.out.printf("Count=%d, Max=%d, Min=%d, Sum=%d, Average=%.2f",
                summaryStatistics.getCount(),
                summaryStatistics.getMax(),
                summaryStatistics.getMin(),
                summaryStatistics.getSum(),
                summaryStatistics.getAverage());
    }

    /**
     * java 8 java.time test
     */
    private void testDate() {

        final Clock clock = Clock.systemUTC();
        System.out.println("clock instant: " + clock.instant());
        System.out.println("current mills: " + clock.millis());

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();

        System.out.println("local date time: " + localDateTime);
        System.out.println("local date is: " + localDate);
        System.out.println("local time is: " + localTime);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("local date string " + localDateTime.format(formatter));

        String dateTimeStr = "2017-08-16 23:59:59";
        LocalDateTime dt = LocalDateTime.parse(dateTimeStr,formatter);
        long aaa = dt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        LocalDate ld = LocalDate.parse(dateTimeStr,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(ld);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date(aaa)));
    }
}
