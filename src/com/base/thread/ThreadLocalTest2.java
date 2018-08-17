package com.base.thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ThreadLocalTest2  extends Thread{
	
	ThreadLocal<String > innerValue = new ThreadLocal<String>() {

		@Override
		protected String initialValue() {
			return "how do you do";
		}
		
	};
	
	
	@Override
	public void run() {
		System.out.println(this.getName()+" inner value is : "+ innerValue.get());
		innerValue.set("my name is "+ this.getName());
		try {
			Thread.sleep(new Random().nextInt(1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(this.getName()+ " inner value  is : "+ innerValue.get());
		innerValue.remove();
		
	}

	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(4);
		for(int i = 0 ;i < 100 ;i ++ ) {
			ThreadLocalTest2 test2 = new ThreadLocalTest2();
			test2.setName("thread" + i);
			pool.execute(test2);
		}
		pool.shutdown();
		while( pool.isTerminated()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}


