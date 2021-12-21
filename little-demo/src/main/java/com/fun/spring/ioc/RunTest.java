package com.fun.spring.ioc;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class RunTest {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beanFactoryTest.xml");
        TestBean bean = (TestBean) ctx.getBean("testBean");
        bean.sayHello();
        bean.setName("hello bean");

        Map<String,Object> properties = Maps.newHashMap();
        properties.put("address","浙江杭州");
        properties.put("age",26);
        Object newBean = ReflectUtil.getTarget(bean,properties);
        System.out.println(JSON.toJSONString(newBean));

        System.out.println("string=====" + isUserClass(new String("111")));
        System.out.println("TestBean======" + isUserClass(bean));
        Object newBean2 = ReflectUtil.getTarget(new String("1111"),properties); // error
        System.out.println(JSON.toJSONString(newBean2));

    }

    public static boolean isUserClass(Object obj) {
        return obj != null && obj.getClass().getClassLoader() != null;
    }

}
