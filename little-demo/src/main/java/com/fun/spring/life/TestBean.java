package com.fun.spring.life;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author yehuan
 * @date 2017-04-21 11:11
 */
@Component("testBean")
@Data
public class TestBean {

	private String userName;

	private int age;

}
