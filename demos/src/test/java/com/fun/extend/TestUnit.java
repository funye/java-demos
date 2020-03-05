package com.fun.extend;

import org.junit.Test;

/**
 * @author huanye
 * @date: 2017/7/14 下午5:17
 */
public class TestUnit {

    @Test
    public void testUse() {

        BaseEntity baseEntity = new MyTestEntityOne(100,26);
        TestService testService = new MyTestServiceImplOne();
        testService.testInput(baseEntity);

    }
}
