package com.fun.lang;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Administrator
 *
 * @date 2017/4/26.
 */
public class DeepCloneDemo {

    public static void main(String[] args) {

        Foo foo = new Foo();

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(foo);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Foo f2 = (Foo)ois.readObject();

            foo.y = 100;
            foo.user.setName("xxxxx");

            System.out.println(f2.y);

            System.out.println(f2.user);

            System.in.read();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
