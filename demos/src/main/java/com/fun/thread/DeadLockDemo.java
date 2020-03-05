package com.fun.thread;

/**
 * 实现一个死锁的示例
 *
 * @author yehuan
 * @version v0.0.1
 * @date 2017-03-22 11:38
 */
public class DeadLockDemo {

	public static void main(String[] args) {

		DeadLockDemo d = new DeadLockDemo();

		Thread a = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("thread a running...");
				synchronized (d) {
					System.out.println("thread a get the lock...");
					while (true) {
						// 只是简单的死循环，占用对象d的锁不释放
					}
				}
			}
		});

		Thread b = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("thread b running...");
				synchronized (d) {
					System.out.println("thread b get the lock...");
				}
			}
		});

		a.start();
		try {
			Thread.sleep(3000); // 确保a先启动，直接顺序start不能保证a比b先启动
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		b.start();
	}
}
