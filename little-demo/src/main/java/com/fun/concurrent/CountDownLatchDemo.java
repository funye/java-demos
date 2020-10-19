package com.fun.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 使用和测试
 *
 * @author yehuan
 * @date 2017-04-13 17:50
 */
public class CountDownLatchDemo {

	public static void main(String[] args) {

		CountDownLatchDemo test = new CountDownLatchDemo();

		int N = 10;
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(N);

		for (int i = 0; i < N; i++) {
			new Thread(test.newWorker(startSignal,doneSignal)).start();
		}

		try {
			System.out.println("do something else 1");
			startSignal.countDown();
			System.out.println("do something else 2");
			doneSignal.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public Worker newWorker(CountDownLatch startSignal, CountDownLatch doneSignal){
		return new Worker(startSignal,doneSignal);
	}

	class Worker implements Runnable {

		private final CountDownLatch startSignal;
		private final CountDownLatch doneSignal;

		Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
			this.startSignal = startSignal;
 			this.doneSignal = doneSignal;
 		}
	 	public void run() {
			try {
				startSignal.await();
				doWork();
				doneSignal.countDown();
			} catch (InterruptedException ex) {} // return;
 		}
		void doWork() {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("do something...");
		}
	}

}
