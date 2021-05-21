package com.fun.classload.classfile;

/**
 * @author huanye
 * @date: 2017/7/25 下午5:10
 */
public class TestClass {

    private int m;

    public int inc() {
        return m + 1;
    }

    public static void main(String[] args) {

        Class<?> clazz=TestClass.class;
        System.out.println(clazz.getClassLoader());//获取当前类的加载器
        System.out.println(clazz.getClassLoader().getParent());//获取父类加载器
        System.out.println(clazz.getClassLoader().getParent().getParent());//获取祖父类加载器
    }
}
