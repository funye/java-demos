package com.fun.jvm;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 数据元区溢出示例
 * VM-args: -Xms256m -Xmx256m -Xmn128m -XX:MetaspaceSize=5m -XX:MaxMetaspaceSize=9m -XX:+PrintGCDetails
 */
public class MetaspaceOOM {

   /* public static void main(String[] args) {
        int i = 0;

        try {
            while (true) {
                i++;

                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(MetaspaceOOMObj.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                        return proxy.invokeSuper(obj, args);
                    }
                });
                enhancer.create();
            }
        } catch (Exception e) {
            System.out.println("第" + i + "次时发生异常");
            e.printStackTrace();
        }
    }*/

    static class MetaspaceOOMObj {
//        public static final String test = "test";
    }


    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MetaspaceOOMObj.class);
//        enhancer.setUseCache(false);
        enhancer.setCallback(new MethodInterceptor() {
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                return proxy.invokeSuper(obj, args);
            }
        });
        Object a = enhancer.create();
        System.out.println("a class = " + a.getClass().getName());

        Object b = enhancer.create();
        System.out.println("b class = " + b.getClass().getName());
    }
}
