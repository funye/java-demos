package com.fun.spring.aop02;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component
public class AopAspect {

    public Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
        Object[] obj = pjp.getArgs();
        System.out.println(obj);
        System.out.println("before proceed");
        Object result = pjp.proceed(obj); // around 和 before after的最大区别在于，around可以控制方法是否执行
        System.out.println("after proceed");
        return result;
    }

}
