package com.base.thread.executorframework;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService cachedThreadPool = Executors.newFixedThreadPool(40);
		List <Future<String>> futureList  = new ArrayList<Future<String>>();
		for(int i =0 ;i< 100 ;i++) {
			Future<String> future = cachedThreadPool.submit( new MyCallable("name "+i ));
			futureList.add(future);
		}
		for (Future<String> f : futureList) {
			System.out.println(f.get());
		}
		cachedThreadPool.shutdown();

	}

}

//调用方法会返回一个对象
class MyCallable implements Callable<String>{
	
	private String name ;
	
	public MyCallable(String name ) {
		this.name = name;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String call() throws Exception {
		Thread.sleep(1000);
		return name +" is using " + Thread.currentThread().getName();
	}
}