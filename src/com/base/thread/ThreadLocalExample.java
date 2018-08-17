package com.base.thread;

import java.text.SimpleDateFormat;
import java.util.Random;



public class ThreadLocalExample implements Runnable {
	private static final ThreadLocal<SimpleDateFormat> formatter  = new ThreadLocal<SimpleDateFormat>() {
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH mm");
		}
	};

	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " default formatter is : "+  formatter.get().toPattern());
		try {
			Thread.sleep(new Random().nextInt(1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		formatter.set(new SimpleDateFormat());
		System.out.println(Thread.currentThread().getName() + "after is : "+  formatter.get().toPattern());
		
	};
	
	
	public static void main(String[] args) throws InterruptedException {
		ThreadLocalExample example = new ThreadLocalExample();
		for(int i =0 ;i< 10 ;i++) {
			Thread thread = new Thread(example, "thread"+i);
//			Thread.sleep(1000);
			thread.start();
		}
	}
}
