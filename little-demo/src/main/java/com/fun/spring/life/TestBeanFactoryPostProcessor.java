package com.fun.spring.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator
 *
 * @date 2017/4/26.
 */
@Component
public class TestBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryPostProcessor--->postProcessBeanFactory......");
        BeanDefinition df = beanFactory.getBeanDefinition("testBean");
        // spring 的bean 配置文件中给bean 属性配置的值
        PropertyValue propUserName = df.getPropertyValues().getPropertyValue("userName");
        if (propUserName == null || propUserName.getValue() == null) {
            // 没有配置值，就自己添加一个
            df.getPropertyValues().addPropertyValue(new PropertyValue("userName", "fun"));
        } else {
            System.out.println("prop name=" + propUserName.getName() + ", value=" + propUserName.getValue());
        }

    }
}
