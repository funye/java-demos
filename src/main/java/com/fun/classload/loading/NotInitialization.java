package com.fun.classload.loading;

/**
 * @author huanye
 * @date: 2017/7/25 下午6:02
 */
public class NotInitialization {

    public static void main(String[] args) {

        /**
         * 1. 被动引用，通过子类引用父类的静态字段，不会导致子类的初始化
         */
        System.out.println(SubClass.value);
        /**
         * 对用静态变量，直接定义他的类被初始化
         * output:
         * SuperClass init..
         * 100
         */

        /**
         * 2. 通过定义数组来引用类，不会出发类的初始化
         */
        SuperClass[] superClasses = new SuperClass[10]; // 此处代码没有出发superclass的初始化动作
        System.out.println(superClasses); // 但是出发了一个 [Lcom.fun.classload.loading.SuperClass;@2c7b84de 的初始化动作，有newarray指令执行

        /**
         * 3. 常量在编译阶段会被存入调用类的常量池，本质上并没有直接引用常量的定义类，因此不会出发定义常量的类的初始化No
         * 在编译之后，NotInitialization 和 ConstClass 之间就没有任何的关系，NotInitialization 对 ConstClass.HELLO
         * 的引用直接指向自己的常量池
         */
        System.out.println(ConstClass.HELLO);

    }
}
