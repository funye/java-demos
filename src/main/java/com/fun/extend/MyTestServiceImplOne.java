package com.fun.extend;

/**
 * @author huanye
 * @date: 2017/7/14 下午5:12
 */
public class MyTestServiceImplOne implements TestService {

    @Override
    public void testInput(BaseEntity entity) {
        MyTestEntityOne one = (MyTestEntityOne) entity;
        System.out.println(one.printOne());
    }
}
