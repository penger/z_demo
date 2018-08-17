package com.base.thread.executorframework;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class CallableTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newFixedThreadPool(1);
		Future<String> submit = pool.submit(new MyCallablex());
		pool.shutdown();
		while(!submit.isDone()) {
			System.out.println("sleep 1s ");
			Thread.sleep(1000);
		}
		System.out.println("get value is :"+ submit.get());
	}

}


class MyCallablex implements Callable<String>{
	@Override
	public String call() throws Exception {
		Thread.sleep(5*1000);
		return "hello callabel";
	}
}