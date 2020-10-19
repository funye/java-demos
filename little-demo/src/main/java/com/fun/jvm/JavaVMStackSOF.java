package com.fun.jvm;

/**
 * 栈溢出示例
 * VM-args: -Xss128k  -XX:+PrintGCDetails -Xloggc:log/gc.log
 * 修改Xss的值，查看调用的栈的深度
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
//			throw e;
		}
	}
}
