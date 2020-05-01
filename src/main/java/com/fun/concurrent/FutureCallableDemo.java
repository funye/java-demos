package com.fun.concurrent;

import java.util.concurrent.*;

/**
 * callable示例
 *
 * @author yehuan
 * @date 2017-04-01 10:59
 */
public class FutureCallableDemo {

	public static void main(String[] args) {
		FutureCallableDemo test = new FutureCallableDemo();

		ExecutorService executorService = Executors.newFixedThreadPool(2);
		//do test
//		test.testCallableTask(executorService);
		test.testRunableTask(executorService);
		executorService.shutdown();
	}

	public void testCallableTask(ExecutorService executorService) {
		Callable<String> task = new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("invoke method call, " + System.currentTimeMillis());
				Thread.sleep(3000);
				System.out.println("method call is going to return, " + System.currentTimeMillis());
				return "SUCCESS";
			}
		};
		Future<String> future = executorService.submit(task);

		System.out.println("main is going to get callable future result, " + System.currentTimeMillis());
		try {
			System.out.println("callable result = " + future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("main got the future result, " + System.currentTimeMillis());
	}

	public void testRunableTask(ExecutorService executorService) {
		Runnable runTask = new Runnable() {
			@Override
			public void run() {
				System.out.println("invoke method run, " + System.currentTimeMillis());
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("method run is going to end, " + System.currentTimeMillis());
			}
		};

		Future<Integer> runFuture = executorService.submit(runTask,new Integer(100));
//		Future<?> runFuture = executorService.submit(runTask);

		try {
			System.out.println("main is going to get runnable future result, " + System.currentTimeMillis());
			System.out.println("runnable result = " + runFuture.get());
			System.out.println("main got the future result, " + System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
