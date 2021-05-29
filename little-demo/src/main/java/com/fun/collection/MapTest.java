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
//		testHashMapPut();

		testTreeMap();
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

		System.out.println(tree.get("key1"));
		System.out.println(tree.higherKey("key1"));
		System.out.println(tree.lowerKey("key2"));
		System.out.println(tree.floorKey("key2"));

		TreeMap<String, Object> tree2 = new TreeMap<>(String::compareTo);
		tree2.put("key1", "aaa");
		tree2.put("key2", "aaa");
		tree2.put("key3", "aaa");

		tree2.forEach((k,v)-> {
			System.out.println("key=" + k + ", value=" + v);
		});

	}
}
