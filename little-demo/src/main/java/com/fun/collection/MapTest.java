package com.fun.collection;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

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

	public static void testTreeMap() {
		TreeMap<String, Object> tree = new TreeMap<>();
		tree.put("key1", "aaa");
		tree.put("key2", "aaa");
		tree.put("key3", "aaa");

		TreeMap<String, Object> tree2 = new TreeMap<>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return 0;
			}
		});
		tree.put("key1", "aaa");
		tree.put("key2", "aaa");
		tree.put("key3", "aaa");

	}
}
