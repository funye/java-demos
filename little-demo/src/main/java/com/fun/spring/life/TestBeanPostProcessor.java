package com.fun.spring.life;

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
        System.out.println("BeanPostProcessor -->postProcessBeforeInitialization -----" + beanName);
        if (bean instanceof TestBean) {
            ((TestBean) bean).setAge(30);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeanPostProcessor--->postProcessAfterInitialization-----" + beanName);
        return bean;
    }
}
