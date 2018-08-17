package com.base.thread.executorframework;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureTest2 {

	public static void main(String[] args) {
		MyCallable2 c1 = new MyCallable2(1000l);
		MyCallable2 c2 = new MyCallable2(2000l);
		
		FutureTask<String> f1 = new FutureTask<>(c1);
		FutureTask<String> f2 = new FutureTask<>(c2);
		
		ExecutorService pool = Executors.newFixedThreadPool(2);
		
		pool.execute(f1);
		pool.execute(f2);
		
		while(true) {
			System.out.println("f1 done : "+ f1.isDone() + "   f2 done :"+ f2.isDone());
			try {
				if( f1.isDone() && f2.isDone()) {
					System.out.println("done ");
					pool.shutdown();
					return;
				}
				
				if(! f1.isDone()) {
					System.out.println("f1 output  is : "+ f1.get());
				}
				System.out.println("waiting for f2 to complete");
				String s = f2.get(200l, TimeUnit.MILLISECONDS);
				if(s != null) {
					System.out.println("f2 output is :"+ s);
				}
			} catch (InterruptedException | ExecutionException  e) {
				e.printStackTrace();
			}catch(TimeoutException e) {
				
			}
		}

	}

}


class MyCallable2 implements Callable<String >{
	
	private long waitTime ;
	
	public MyCallable2(long waitTime) {
		this.waitTime = waitTime;
	}
	
	@Override
	public String call() throws Exception {
		Thread.sleep(waitTime);
		return "Thread " + Thread.currentThread().getName()+ " wait"+ waitTime;
	}
}