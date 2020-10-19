package com.fun.lang;

import java.lang.ref.SoftReference;

/**
 * Created by Administrator
 *
 * @date 2017/4/26.
 */
public class ReferenceDemo {

    private String name;
    private ReferenceDemo instance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("set name to "+ name);
        this.name = name;
    }

    public void setInstance(ReferenceDemo instance) {
        this.instance = instance;
    }

    public ReferenceDemo getInstance() {
        return instance;
    }

    public ReferenceDemo() {
    }
    public ReferenceDemo(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        ReferenceDemo r1 = new ReferenceDemo("fun");
        r1.setInstance(new ReferenceDemo("fun field"));

        ReferenceDemo r2 = r1;

        System.out.println(r2.getInstance().getName());

        r1.getInstance().setName("sw");

        System.out.println(r2.getInstance().getName());




        // --------soft reference test
        ReferenceDemo ref = new ReferenceDemo("fun");
        SoftReference<ReferenceDemo> sref = new SoftReference<>(ref);

        ref = null;

        //System.gc();

        if (sref != null) {
            if (sref.get() != null) {
                System.out.println("ref is not null");
                sref.get().setName("test");
            }
        }
        // soft reference test end

    }
}
