package com.fun.spring;

import org.springframework.stereotype.Component;

/**
 * @author yehuan
 * @date 2017-04-21 11:11
 */
@Component("testBean")
public class TestBean {
	private String name;

	public void testMethod(String name) {
		System.out.println("hello " + name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
