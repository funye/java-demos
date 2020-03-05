package com.fun.lang;

/**
 * Created by fun on 2017/2/17.
 */
public class OuterClass {

	private int id;

	private InnerClass getInnerClass(final String name, final int age) {
		return new InnerClass() {

			String innerName;
			int innerAge;
			// 构造代码块初始化
			{
				innerName = name;
				innerAge = age;
			}

			@Override
			public String getInnerName() {
				return innerName;
			}

			@Override
			public int getInnerAge() {
				return innerAge;
			}
		};
	}

	public static void main(String[] args) {
		OuterClass outerClass = new OuterClass();

		InnerClass innerClass = outerClass.getInnerClass("fun",26);
		System.out.println(innerClass.getInnerName());

		InnerClass innerClass2 = outerClass.getInnerClass("BruceLee",26);
		System.out.println(innerClass2.getInnerName());
	}
}

interface InnerClass{
	String getInnerName();
	int getInnerAge();
}
