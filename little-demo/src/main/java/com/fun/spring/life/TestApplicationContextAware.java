package com.fun.spring.life;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class TestApplicationContextAware implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("TestApplicationContextAware--->setApplicationContext");
        // 通过setApplicationContext 方法把applicationContext 注入，之后使用TestApplicationContextAware.applicationContext
        // 就相当于直接使用ApplicationContext
        this.applicationContext = applicationContext;
    }

    // 例如这个示例为 一个获取bean的工具
    public Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }
}
