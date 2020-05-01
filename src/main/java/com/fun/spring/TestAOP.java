package com.fun.spring;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * aspectJ 实现的AOP
 * Created by fun
 *
 * @date 2017/5/5.
 */
@Aspect
@Component
public class TestAOP {


    @Pointcut("execution(* com.fun.spring.MyBean.testMethod())")
    public void before() {
    }

    @Pointcut("execution(* com.fun.spring.TestBean.testMethod())")
    public void after() {
    }

    @Pointcut("execution(* com.fun.spring.TestBean.testMethod(*))")
    public void around() {
    }


    @Before("before() ")
    public void beforeMethod(JoinPoint pjp) throws Throwable {
        System.out.println("before method....");

    }

//    @After("after() ")
    public void afterMethod(JoinPoint pjp) throws Throwable {
        System.out.println("after method....");
    }

    @Around("around()")
    public Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
        Object[] obj = pjp.getArgs();
        System.out.println(obj);
        System.out.println("before proceed");
        Object result = pjp.proceed(obj); // around 和 before after的最大区别在于，around可以控制方法是否执行
        System.out.println("after proceed");
        return null;
    }
}
