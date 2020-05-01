package com.fun.lang;

/**
 * finally是不是一定执行
 *
 * @author yehuan
 * @date 2017-03-27 17:17
 */
public class TryCatchDemo {

	public static void main(String[] args) {

		try {
			System.out.println("into try block...");
//			System.exit(0); // 打开此注释，会发现，finally里面的代码并没有执行，这个很好理解，虚拟机都退出了，怎么执行代码
			throw new Error();
		}catch (Throwable e) {
			System.out.println("into catch block...");

		}finally {
			System.out.println("into finally block...");
		}

	}

	/*
	 * output:
	 *into try block...
	 *into catch block...
	 *into finally block...
	 */

	/*
	 * 结论：
	 * 1. finally里面的代码不一定执行的，例如虚拟机退出了
	 * 2. Throwable两个子类 Error 和 Exception， Error也是可以被捕获的，
	 * 只是catch的是当前捕获的Error或者更上级的类才行捕获Error
	 */

	class MyException extends Throwable {

	}
}
