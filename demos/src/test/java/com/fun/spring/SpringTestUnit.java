package com.fun.spring;

import com.fun.spring.listener.MyApplicationEvent;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.List;

/**
 * @author yehuan
 * @date 2017-04-21 11:17
 */
public class SpringTestUnit {

	@Test
	public void testIoCCreate() {
		ApplicationContext acx = new FileSystemXmlApplicationContext("classpath:application.xml");

		TestBean test = (TestBean) acx.getBean("testBean");
		test.testMethod("world");
		MyBean bean = new MyBean();
		bean.testMethod();
	}

	@Test
	public void testProgramWayIoC() {
		// 确定资源
		Resource res = new ClassPathResource("application.xml");
		// 创建一个BeanFactory,设置部分不想使用默认值的属性
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		factory.setAllowBeanDefinitionOverriding(false);
		// 创建BeanDefinitionReader
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		// 加载DeanDefinition并向IoC容器注册
		reader.loadBeanDefinitions(res);
		// 使用bean
		TestBean test = factory.getBean("testBean",TestBean.class);
		test.testMethod("hello");

	}

	@Autowired
    List<ApplicationListener> lists;


	@Test
	public void testAAA() {
//		ApplicationContext ctx = new AnnotationConfigApplicationContext("com.fun.spring.listener");
//		ctx.publishEvent(new MyApplicationEvent("hello"));
	}
}
