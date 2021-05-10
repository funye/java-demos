package com.fun.spring.aop04;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Component
public class AopAspect implements MethodInterceptor, MethodBeforeAdvice {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        Object[] obj = invocation.getArguments();
        System.out.println("before proceed, parameters=" + Arrays.asList(obj));
        System.out.println("before proceed");
        Object result = invocation.proceed();
        System.out.println("after proceed");
        return result;
    }

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("before method");
        System.out.println("before method, parameters=" + Arrays.asList(args));
    }
}
