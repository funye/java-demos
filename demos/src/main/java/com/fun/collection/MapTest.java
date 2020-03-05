package com.fun.collection;

import java.util.HashMap;

/**
 * Map相关测试类
 *
 * @author yehuan
 * @version v1.0.0
 * @date 2017-02-27 17:39
 */
public class MapTest {

	public static void main(String[] args) {
		testHashMapPut();

	}


	public static void testHashMapPut() {

		HashMap<String ,String> map = new HashMap<>(2);
		String v= map.put("key1","hello");
		System.out.println(v);
		String v2 = map.put("key1","world");
		System.out.println(v2);
		System.out.println(map.get("key1"));

	}
}
