package com.fun.lang.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator
 *
 * @date 2017/4/26.
 */
public class ReferenceDemo {

    /*
     * 强引用：
     *
     * 软引用：
     *
     * 弱引用：
     *
     * 虚引用
     *
     *
     */

    public static void main(String[] args) throws InterruptedException {

        System.out.println("===========Reference==========");
        Object o = new Object();
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(o);
        o = null;
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(o);


        System.out.println("===========SoftReference==========");
        Object object1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(object1);

        System.out.println(object1);
        System.out.println(softReference.get());

        object1 = null;
        System.gc();
        TimeUnit.SECONDS.sleep(1);

        System.out.println(object1);
        System.out.println(softReference.get());

        System.out.println("===========WeakReference==========");
        Object object2 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(object2);

        System.out.println(object2);
        System.out.println(weakReference.get());

        object2 = null;
        System.gc();
        TimeUnit.SECONDS.sleep(1);

        System.out.println(object2);
        System.out.println(weakReference.get());

        System.out.println("=================HashMap===============");

        HashMap<String, Object> map = new HashMap<>();
        String key = new String("test-key");
        map.put(key, "hello");
        System.out.println(map);
        key = null;
        System.gc();
        System.out.println(map);

        System.out.println("================WeakHashMap================");

        WeakHashMap<String, Object> map2 = new WeakHashMap<>();
        String key2 = new String("test-key");
        map2.put(key2, "hello");
        System.out.println(map2);
        key2 = null;
        System.gc();
        System.out.println(map2);

        System.out.println("================PhantomReference ================");
        Object object3 = new Object();
        ReferenceQueue queue = new ReferenceQueue();
        PhantomReference<Object> phantomReference = new PhantomReference<>(object3, queue);

        System.out.println(object3);
        System.out.println(phantomReference.get());
        System.out.println(queue.poll());

        object3 = null;
        System.gc();
        TimeUnit.SECONDS.sleep(1);

        System.out.println(object3);
        System.out.println(phantomReference.get());
        System.out.println(queue.poll());
    }


}
