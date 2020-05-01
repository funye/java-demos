package com.fun.lang;

import com.fun.collection.User;

import java.io.Serializable;

/**
 * Created by fun on 2017/2/13.
 */
public class Foo implements Serializable{

    private static final long serialVersionUID = -2L;

    public static int w = 1;
    public static transient int x = 2;
    public int y = 3;
    public transient int z = 4;

    public static User user = new User("yehuan",11);

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
