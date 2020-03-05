package com.fun.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yehuan on 2017/2/16.
 * VM-args:-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {
	static class OOMObject{

	}

	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<>();
		while(true){
			list.add(new OOMObject());
		}
	}
}
