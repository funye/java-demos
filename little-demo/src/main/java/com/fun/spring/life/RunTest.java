package com.fun.spring.life;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RunTest {

    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("application-life.xml");
        ctx.registerShutdownHook(); // shutdownHook 可以执行context.close(), context.close() 才会触发DisposableBean的destroy() 方法

        TestBean bean = (TestBean) ctx.getBean("testBean");
        System.out.println(bean);

        // test aware use
        TestApplicationContextAware contextAware = ctx.getBean(TestApplicationContextAware.class);
        TestBean bean2 = (TestBean)contextAware.getBean("testBean");
        bean2.getAge();
        System.out.println(bean2);

        // test initializingBean and DisposableBean
        TestInitializingBean initializingBean = ctx.getBean(TestInitializingBean.class);
        System.out.println("TestInitializingBean.name=" + initializingBean.getName());

        TestFactoryBean tfb = ctx.getBean(TestFactoryBean.class);
        TestBean tb1 = tfb.getObject();
        TestBean tb2 = tfb.getObject();
        System.out.println("TestBean1 get from TestFactoryBean, userName="+tb1.getUserName() + ", class=" + tb1.getClass());
        System.out.println("TestBean2 get from TestFactoryBean, userName="+tb2.getUserName() + ", class=" + tb2.getClass());


    }
}
