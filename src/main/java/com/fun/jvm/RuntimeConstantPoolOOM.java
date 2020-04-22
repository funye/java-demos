package com.fun.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行时常量池溢出（方法区、数据元区）
 * Created by fun on 2017/2/16.
 * VM-args:-XX:PermSize=10M -XX:MaxPermSize=10M (jdk>=1.8之后没有此配置) -XX:+PrintGCDetails
 */
public class RuntimeConstantPoolOOM {

	public static void main(String[] args) {
		testIntern();
	}

	/**
	 * 此方法在JDK1.6之前会出现OutOfMemoryError,1.7之后一直循环下去. 1.7应为常量池在堆区，由堆大小决定
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
}
