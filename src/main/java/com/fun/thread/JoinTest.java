package com.fun.thread;

/**
 * join方法测试
 *
 * @author yehuan
 * @version v0.0.1
 * @date 2017-03-21 9:37
 */
public class JoinTest {
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new Runnable() {
			public void run()
			{
				System.out.println("First task started");
				System.out.println("Sleeping for 2 seconds");
				try
				{
					Thread.sleep(30000);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				System.out.println("First task completed");
			}
		});
		Thread t2 = new Thread(new Runnable(){
			public void run()
			{
				try {
					t1.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Second task completed");
			}
		});
		t1.start();
		t2.start();
	}
}
