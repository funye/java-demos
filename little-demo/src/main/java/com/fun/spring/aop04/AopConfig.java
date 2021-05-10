package com.fun.spring.aop04;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AopConfig {

    @Autowired
    private AopBean aopBean;

    @Autowired
    private AopAspect aopAspect;

    @Bean("myAdvisor")
    public DefaultPointcutAdvisor defaultPointcutAdvisor() {
        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        pointcut.setPattern(".*say.*");

        DefaultPointcutAdvisor advisor =  new DefaultPointcutAdvisor(pointcut, aopAspect);
        return advisor;
    }

    @Bean("aopBeanProxy")
    public ProxyFactoryBean ProxyFactoryBean() throws ClassNotFoundException {
        ProxyFactoryBean proxy = new ProxyFactoryBean();
        proxy.setTarget(aopBean);
        proxy.setProxyInterfaces(new Class[]{AopBean.class});
        proxy.setInterceptorNames("myAdvisor");
        return proxy;
    }

}
