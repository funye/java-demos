package com.fun.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yehuan on 2017/2/16.
 * VM-args:-XX:PermSize=10M -XX:MaxPermSize=10M >=1.8之后没有此配置
 */
public class RuntimeConstantPoolOOM {

	public static void main(String[] args) {
		testIntern2();
	}

	/**
	 * 此方法在JDK1.6之前会出现OutOfMemoryError,1.7之后一直循环下去
	 */
	public static void testIntern() {
		// 使用list来保持着常量池引用，避免Full GC回收常量池
		List<String> list = new ArrayList<>();
		int i = 0;
		while (true){
			System.out.println(i);
			list.add(String.valueOf(i++).intern());
		}
	}

	public static void testIntern2() {
		String str1 = new StringBuilder("计算机").append("软件").toString();
		System.out.println(str1.intern() == str1);

		String str2 = new StringBuilder("ja").append("va").toString();
		System.out.println(str2.intern() == str2);

	}
}
