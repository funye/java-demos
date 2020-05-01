package com.fun.spring;

import org.springframework.stereotype.Component;

/**
 * @author yehuan
 * @date 2017-04-21 11:11
 */
@Component("testBeanTwo")
public class TestBeanTwo {
	private String name;

	public void testMethod() {
		System.out.println("hello " + name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
