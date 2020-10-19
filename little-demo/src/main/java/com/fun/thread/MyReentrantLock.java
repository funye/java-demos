package com.fun.thread;

/**
 * 测试锁的重入
 *
 * @author yehuan
 * @version v0.0.1
 * @date 2017-03-22 12:02
 */
public class MyReentrantLock {

/*	public static void main(String[] args) {
		LockOverride lo = new LockOverride();

		synchronized (lo) {
			System.out.println("outter get lock on lo...."+System.currentTimeMillis());

			new Thread(new Runnable() {
				@Override
				public void run() {
					synchronized (lo) {
						System.out.println("inner get lock on lo....."+System.currentTimeMillis());
					}
				}
			}).start();

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}*/

	public static class SuperClass {
		public synchronized void doSomething() {
			System.out.println("super do something....");
		}
	}

	public static class SubClass extends SuperClass {
		@Override
		public synchronized void doSomething() {
			System.out.println("sub do something and call super.doSomething()....");
			super.doSomething();
			System.out.println(this.getClass());
			System.out.println(super.getClass());
		}
	}

	public static void main(String[] args) {
		SubClass sub = new SubClass();
		sub.doSomething();
	}
}

