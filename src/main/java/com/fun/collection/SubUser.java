package com.fun.collection;

/**
 * 用户子类，测试private
 *
 * @author yehuan
 * @version v1.0.0
 * @date 2017-02-27 17:26
 */
public class SubUser extends User {
	private int sex;

	public SubUser(int sex) {
		this.sex = sex;
	}

	public static void main(String[] args) {
		SubUser user = new SubUser(1);
		user.setName("aaa");

		// 子类继承了age属性，但是不能访问，只能通过父类的非private方法访问
		System.out.println(user.getName());
	}
}
