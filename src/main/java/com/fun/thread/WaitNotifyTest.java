package com.fun.thread;

/**
 * 测试wait-notify模式和循环检测等待的区别
 *
 * @author fun
 * @version v0.0.1
 * @date 2017-03-23 15:24
 */
public class WaitNotifyTest {

	private volatile static int f = 0;

	public static void main(String[] args) {


		WaitNotifyTest test = new WaitNotifyTest();

//		分别测试两种情况
		test.waitNotifyWayTest(new String("way1"));

//		test.whileWayTest(new String("way2"));

	}

	public static int getF(String caller) {
		System.out.println(caller + " get f is: " + f );
		return f;
	}

	public void waitNotifyWayTest(Object o) {
		Thread a = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (o) {
					System.out.println("A is running....."+o.toString());
					while (getF("A-"+o.toString()) != 1) {
						try {
							System.out.println("A wait...."+o.toString());
							o.wait(0);
							System.out.println("A awake...."+o.toString());
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				System.out.println("A give money to B..."+o.toString());


			}
		});

		Thread b = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("B simulate get the cigarette,use time 3s ..."+o.toString());
					Thread.sleep(3000);
					System.out.println("B got the cigarette..."+o.toString());
					f = 1;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (o) {
					if (f == 1) {
						o.notify();
						System.out.println("B notify A...."+o.toString());
					}
				}

			}
		});

		a.start();
		b.start();
	}

	public void whileWayTest(Object o) {
		Thread a = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("A is running....."+o.toString());
				while (getF("A-"+o.toString()) != 1) {
//					System.out.println("A waiting.....");
				}
				System.out.println("A give money to B..."+o.toString());
			}
		});

		Thread b = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("B simulate get the cigarette,use time 3s ..."+o.toString());
					Thread.sleep(3000);
					System.out.println("B got the cigarette..."+o.toString());
					f = 1;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		a.start();
		b.start();
	}

}

