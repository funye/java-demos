package com.fun.lang.extend;

/**
 * @author huanye
 * @date: 2017/7/14 下午5:12
 */
public class MyTestServiceImplTwo implements TestService {
    @Override
    public void testInput(BaseEntity entity) {

        MyTestEntityTwo two = (MyTestEntityTwo) entity;
        System.out.println(two.printTwo());

    }
}
