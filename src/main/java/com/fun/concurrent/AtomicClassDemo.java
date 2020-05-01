package com.fun.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子类操作
 *
 * @author fun
 * @date 2017-04-01 12:07
 */
public class AtomicClassDemo {

	public static void main(String[] args) {
		AtomicInteger aint = new AtomicInteger(100);
		boolean b = aint.compareAndSet(100,200); // 修改expect与当前值不同测试
		if (b) {
			System.out.println(aint.get());
		}
		System.out.println(aint.getAndAdd(100));
		System.out.println(aint.get());

		System.out.println("-------------------");
		AtomicClassDemo ref1 = new AtomicClassDemo();
		AtomicClassDemo ref2 = new AtomicClassDemo();
		System.out.println("ref1=" + ref1);
		System.out.println("ref2=" + ref2);
		AtomicReference<AtomicClassDemo> ref3 = new AtomicReference<>(ref1);
		System.out.println("ref3 before set=" + ref3);
		boolean b2 = ref3.compareAndSet(ref2,ref1); // 修改expect为ref1测试
		System.out.println(b2);
		System.out.println("ref3 after set =" + ref3);
	}

}
/**
 output:
 200
 200
 300
 -------------------
 ref1=com.fun.concurrent.AtomicClassDemo@74a14482
 ref2=com.fun.concurrent.AtomicClassDemo@1540e19d
 ref3 before set=com.fun.concurrent.AtomicClassDemo@74a14482
 false
 ref3 after set =com.fun.concurrent.AtomicClassDemo@74a14482
 */
