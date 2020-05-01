package com.fun.lang;

import java.util.Arrays;
import java.util.List;

/**
 * array test
 * Created by yeuhan on 2017/2/24.
 */
public class ArrayDemo {

	public static void main(String[] args) {
		int[] datas = new int[]{1,2,3,4,5}; // 比较换成Integer的效果，关注asList方法
		List list = Arrays.asList(datas);
		System.out.println(list.size());
	}

}
