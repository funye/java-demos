package com.fun.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆溢出示例
 * VM-args:-Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\Users\80279309\Documents\tempwork\headoom.dump
 */
public class HeapOOM {
	static class OOMObject{
		private static String test="test";
	}

	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<>();
		while(true){
			list.add(new OOMObject());
		}
	}
}
