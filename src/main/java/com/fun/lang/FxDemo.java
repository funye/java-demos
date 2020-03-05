package com.fun.lang;

/**
 * 泛型
 * Created by fun on 2017/2/17.
 */
public class FxDemo<T> {

	private T obj;

	public FxDemo(T obj) {
		this.obj = obj;
	}

	public T getObj() {
		return obj;
	}

	public void showType() {
		System.out.println("T 的实际类型为： "+ obj.getClass().getName());
	}

	public static void main(String[] args) {
		FxDemo intObj = new FxDemo(new Integer(100));
		System.out.println(intObj.getObj());
		intObj.showType();

		FxDemo strObj = new FxDemo("BruceLee");
		System.out.println(strObj.getObj());
		strObj.showType();

		System.out.println("-------------------------");

		NonFxDemo intObj2 = new NonFxDemo(new Integer(100));
		System.out.println(intObj2.getObj());
		intObj2.showType();

		NonFxDemo strObj2 = new NonFxDemo("BruceLee");
		System.out.println(strObj2.getObj());
		strObj2.showType();
	}
}

class NonFxDemo {
	private Object obj;

	public NonFxDemo(Object obj) {
		this.obj = obj;
	}

	public Object getObj() {
		return obj;
	}

	public void showType() {
		System.out.println("T 的实际类型为： "+ obj.getClass().getName());
	}

}
