package com.fun.java8;

public class FunctionalInterfaceTest {



    @FunctionalInterface
    public interface TestApi1 {
        String test(String name);
    }

    @FunctionalInterface
    public interface TestApi2 {
        void test(String name);
    }

    public static void main(String[] args) {

        System.out.println(testFun1(new User()::printName, "fun"));
        testFun2(System.out::println, "fun");
    }

    public static String testFun1(TestApi1 api, String name) {
        return api.test(name);
    }

    public static void testFun2(TestApi2 api, String name) {
        api.test(name);
    }

    static class User {

        public String printName(String name) {
            System.out.println("name=" + name);
            return "hello " + name;
        }
    }
}
