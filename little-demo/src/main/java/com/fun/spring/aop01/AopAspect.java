package com.fun.spring.aop01;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Aspect
public class AopAspect {

    /*
       @Before @After @Around @AfterReturning @AfterThrowing
     */

    @Around("execution(* com.fun.spring.aop01.AopBean.*(*))")
    public Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
        Object[] obj = pjp.getArgs();
        System.out.println(obj);
        System.out.println("before proceed");
        Object result = pjp.proceed(obj); // around 和 before after的最大区别在于，around可以控制方法是否执行
        System.out.println("after proceed");
        return result;
    }

    @Before("execution(* com.fun.spring.aop01.AopBean.*(*))")
    public void beforeMethod(JoinPoint point) throws Throwable {
        String methodName = point.getSignature().getName();
        List<Object> args = Arrays.asList(point.getArgs());
        System.out.println("调用前连接点方法为：" + methodName + ", 参数为：" + args);
    }

    @After("execution(* com.fun.spring.aop01.AopBean.*(*))")
    public void afterMethod(JoinPoint point) throws Throwable {
        String methodName = point.getSignature().getName();
        List<Object> args = Arrays.asList(point.getArgs());
        System.out.println("调用后连接点方法为：" + methodName + ", 参数为：" + args);
    }
}
