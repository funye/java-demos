package com.fun.lang;

import java.io.*;

/**
 * Created by fun on 2017/2/13.
 */
public class TransDemo {

    public static void main(String[] args) {
        TransDemo test = new TransDemo();
        Foo foo = new Foo();
        System.out.println("-----before serialize:");
        test.printObject(foo);

        test.putSerializedObject(foo); // 第二修改Foo的serializVersionUID=-2L,在注释掉此段，只执行反序列化。观察
        System.out.println("-----after serialize:");
        test.printObject(test.getSerializedObject());
    }

    public void putSerializedObject(Foo foo) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("x.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(foo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Foo getSerializedObject() {
        Foo fooNew = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("x.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            System.out.println();
            fooNew = (Foo) ois.readObject();
            return fooNew;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void printObject(Foo foo) {
        System.out.printf("w: %d%n", foo.w);
        System.out.printf("x: %d%n", foo.x);
        System.out.printf("y: %d%n", foo.y);
        System.out.printf("z: %d%n", foo.z);
    }

}
