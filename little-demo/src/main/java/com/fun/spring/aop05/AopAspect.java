package com.fun.spring.aop05;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Aspect
public class AopAspect {


    @Around("execution(* com.fun.spring.aop05.AopBeanA.*(*))")
    public Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
        Object[] obj = pjp.getArgs();
        String methodName = pjp.getSignature().getName();

        System.out.println("调用前连接点方法为：" + methodName + ", 参数为：" + Arrays.asList(obj));
        Object result = pjp.proceed(obj); // around 和 before after的最大区别在于，around可以控制方法是否执行
        System.out.println("调用后连接点方法为：" + methodName + ", 参数为：" + Arrays.asList(obj));
        return result;
    }

    @After("execution(* com.fun.spring.aop05.AopBeanC.*(*))")
    public void afterMethod(JoinPoint point) throws Throwable {
        String methodName = point.getSignature().getName();
        List<Object> args = Arrays.asList(point.getArgs());
        System.out.println("调用后连接点方法为：" + methodName + ", 参数为：" + args);
    }
}
