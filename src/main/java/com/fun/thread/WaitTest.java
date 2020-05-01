package com.fun.thread;

/**
 * Thread wait方法学习
 *
 * @author yehuan
 * @version v0.0.1
 * @date 2017-03-21 14:45
 */
public class WaitTest {
	private static volatile Integer o = 0;

	public static void main(String[] args) {

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("thread one start..."+System.currentTimeMillis());
				try {
					Thread.sleep(3000);
					synchronized (o) {
						System.out.println("notify thread two on object o before..."+System.currentTimeMillis());
						o.notify();
						System.out.println("notify thread two on object o end..."+System.currentTimeMillis());
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("thread one end..."+System.currentTimeMillis());

			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (o) {
					System.out.println("thread two start..."+System.currentTimeMillis());
					try {
						o.wait(0);
						System.out.println("awake...."+System.currentTimeMillis());
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("thread two end..."+System.currentTimeMillis());
				}
			}
		});

		t2.start();
		t1.start();

	}

}
