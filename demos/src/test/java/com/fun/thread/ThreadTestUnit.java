package com.fun.thread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程相关测试类
 *
 * @author yehuan
 * @version v1.0.0
 * @date 2017-03-14 11:07
 */
public class ThreadTestUnit {


	@Test
	public void testMultiCallStart() {

		Thread t = new Thread(new TestTask(1,10));
//		t.start();
//		t.start(); // 测试不能再次调用start

		t.run();
		t.run();

	}

	@Test
	public void testRunAndStartDiff() {

		System.out.println("main threadId: "+Thread.currentThread().getId()+
						",main threadName: "+Thread.currentThread().getName()+
						",isDaemon " + Thread.currentThread().isDaemon());
		Thread t = new Thread(new TestTask(1,10));
		t.start();

		Thread t2 = new Thread(new TestTask(2,20));
		t2.run();
	}

	@Test
	public void test1() throws InterruptedException {
		// 1. 每个线程都有一个新的Runnable
		for (int i = 0; i < 10; i++) {
			new Thread(new TestTask(i + 1, 10)).start();
			Thread.sleep(200);
		}
	}

	@Test
	public void test2()  throws InterruptedException {
		TestTask testTask = new TestTask(1, 10);
		for (int i = 0; i < 10; i++) {
			new Thread(testTask).start();
			Thread.sleep(200);
		}
	}

	@Test
	public void test3()  throws InterruptedException {
		ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
		executorService.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
		for (int i = 0; i < 10; i++) {
			executorService.execute(new TestTask(1, 10));
		}
		executorService.shutdown();
	}

	@Test
	public void test4()  throws InterruptedException {
		ExecutorService executorService2 = Executors.newFixedThreadPool(2);
		TestTask testTask2 = new TestTask(1, 10);
		for (int i = 0; i < 10; i++) {
			executorService2.execute(testTask2);
		}
		executorService2.shutdown();
	}

	@Test
	public void testJoin() throws InterruptedException {
		Thread t = new Thread(new Runnable() {
			public void run()
			{
				System.out.println("First task started");
				System.out.println("Sleeping for 2 seconds");
				try
				{
					Thread.sleep(2000);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				System.out.println("First task completed");
			}
		});
		Thread t1 = new Thread(new Runnable(){
			public void run()
			{
				System.out.println("Second task completed");
			}
		});
		t.start();
//		t.join();
		t1.start();
	}

	@Test
	public void testYield() {

	}
}
