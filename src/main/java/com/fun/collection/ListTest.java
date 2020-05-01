package com.fun.collection;

import java.util.*;

/**
 * 测试list
 *
 * @author yehuan
 * @date 2017-02-27
 */
public class ListTest {
	public static void main(String[] args) {
//		testAsList();

//		testAddRemoveSpeed();

//        testRemove();

        testSublist();
	}

	/**
	 * 比较添加和删除操作的效率
	 */
	public static void testAddRemoveSpeed() {
		int size = 1000000;
		long time1 = System.currentTimeMillis();
		List<Long> arrList = new ArrayList<>();
		for (int i = 0; i < size; i++ ) {
			arrList.add(new Long(i));
		}
		long time2 = System.currentTimeMillis();
		System.out.println("=====> ArrayList use time " + (time2-time1) + " ms");

		long time3 = System.currentTimeMillis();
		List<Long> linkList = new LinkedList<>();
		for (int i = 0; i < size; i++ ) {
			linkList.add(new Long(i));
		}
		long time4 = System.currentTimeMillis();
		System.out.println("=====> LinkedList use time " + (time4-time3) + " ms");

		int index = 999990;
		long time5 = System.currentTimeMillis();
		arrList.add(index,new Long(100));
		long time6 = System.currentTimeMillis();
		System.out.println("=====> ArrayList add index " + index + " use time " + (time6-time5) + " ms");

		long time7 = System.currentTimeMillis();
		linkList.add(index,new Long(100));
		long time8 = System.currentTimeMillis();
		System.out.println("=====> LinkedList add index " + index + " use time " + (time8-time7) + " ms");
	}

	/**
	 * Arrays.asList()方法需要注意的地方
	 */
	public static void testAsList() {
		String[] str = new String[]{"a", "b", "c"};
		List list = Arrays.asList(str);
//		list.add("d");
		str[0] = "f";
		System.out.println(list.toString());

		List<String> a = new ArrayList<>();
		a.add("1");
		a.add("2");
		for (String temp : a) {
			if("1".equals(temp)){ // "1" change to "2", see the result
				a.remove(temp);
			}
		}

		System.out.println(a);
	}

    /**
     * 测试删除
     */
	public static void testRemove(){
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");

        Iterator it = list.iterator();
        while (it.hasNext()){
            it.next();
            it.remove();
        }

        System.out.println(list);
    }

	public static void testSublist(){
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");

		System.out.println(list.subList(1,list.size()));
	}
}
