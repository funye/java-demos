package com.fun.gc;

/**
 * Created by yehuan on 2017/2/16.
 * VM-args: -Xss128k  -Xlog:gc*
 */
public class JavaVMStackSOF {

	private int stackLength = 1;

	public void staticLeak() {
		stackLength++;
		staticLeak();
	}

	public static void main(String[] args) {
		JavaVMStackSOF oom = new JavaVMStackSOF();
		try {
			oom.staticLeak();
		}catch(Throwable e){
			System.out.println("static length: "+ oom.stackLength);
			throw e;
		}
	}
}
