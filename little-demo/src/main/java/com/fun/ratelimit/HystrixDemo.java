package com.fun.ratelimit;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesCommandDefault;

public class HystrixDemo {

    public static class CommandHelloWorld extends HystrixCommand<String> {

        private final String name;

        public CommandHelloWorld(String name) {
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                    .andCommandPropertiesDefaults(HystrixPropertiesCommandDefault.Setter()
                            .withCircuitBreakerErrorThresholdPercentage(50)//错误率超过50%,快速失败
                            .withExecutionTimeoutInMilliseconds(2000)
                            .withCircuitBreakerRequestVolumeThreshold(5)
                            .withCircuitBreakerSleepWindowInMilliseconds(3000)// 3s后放部分流量过去重试
                            .withMetricsRollingPercentileBucketSize(10)
                            .withMetricsRollingPercentileWindowInMilliseconds(5000))
            );
            this.name = name;
        }

        @Override
        protected String run() {
            // a real example would do work like a network call here
//            return "Hello " + name + "!";
            try {
                Thread.sleep(1000L);
            } catch (Exception e) {

            }
            if (name.contains("@")) {
                throw new RuntimeException("error");
            } else {
                return "Hello " + name;
            }

        }

        @Override
        protected String getFallback() {
            return "Hello Failure " + name;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CommandHelloWorld cmd = new CommandHelloWorld("Bob");
        String s = cmd.execute();
        System.out.println(s);

        for (int i = 0; i < 10; i++) {
            s = new CommandHelloWorld("Bob@" + i).execute();
            System.out.println(s);
        }

        for (int i = 10; i < 15; i++) {
            s = new CommandHelloWorld("Bob" + i).execute();
            System.out.println(s);
        }

        Thread.sleep(1500L);
        s = new CommandHelloWorld("Bob" + 15).execute();
        System.out.println(s);

        Thread.sleep(1500L);
        for (int i = 16; i < 20; i++) {
            s = new CommandHelloWorld("Bob" + i).execute();
            System.out.println(s);
        }
    }
}
