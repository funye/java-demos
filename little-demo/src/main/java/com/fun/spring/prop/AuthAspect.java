package com.fun.spring.prop;

import com.google.common.base.CaseFormat;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Aspect
public class AuthAspect {


    @Around("@annotation(auth)")
    public Object aroundMethod(ProceedingJoinPoint pjp, Auth auth) throws Throwable {
        String methodName = pjp.getSignature().getName();
        List<Object> args = Arrays.asList(pjp.getArgs());
        System.out.println("调用前连接点方法为：" + methodName + ", 参数为：" + args);

        String isNeedAuth = auth.isNeedAuth();
        String key = isNeedAuth.substring(isNeedAuth.indexOf("{") + 1, isNeedAuth.indexOf("}")).replaceAll(" ","");
        String value = PropertyUtils.getProperty(key);

        System.out.println(key + "=" + value);

        if (Boolean.valueOf(value) && !"fun".equalsIgnoreCase(args.get(0).toString())) {
            System.out.println("============auth failed===========");
            throw new Exception("auth failed");
        } else {
            return pjp.proceed(pjp.getArgs());
        }
    }


}
