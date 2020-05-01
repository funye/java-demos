package com.fun.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator
 *
 * @date 2017/4/26.
 */
@Component
public class TestBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("before -----" + beanName);
        if (bean instanceof TestBean) {
            ((TestBean) bean).setName("testBean");
        }

        if (bean instanceof TestBeanTwo) {
            ((TestBeanTwo) bean).setName("testBeanTwo");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("after-----" + beanName);
        return bean;
    }
}
