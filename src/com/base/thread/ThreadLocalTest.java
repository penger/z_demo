package com.base.thread;

import kafka.utils.threadsafe;

public class ThreadLocalTest {

	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new Thread(new ThreadTest());
		Thread thread2 = new Thread(new ThreadTest());
		thread1.start();
		thread2.start();
		
		thread1.join();
		thread2.join();

	}

}


class ThreadTest implements Runnable{
	
	private ThreadLocal tl = new ThreadLocal<Long>();

	@Override
	public void run() {
		long id = Thread.currentThread().getId();
		tl.set(id);
		try {
			Thread.sleep((long) (Math.random()*100));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(tl.get()+ " id is :"+id );
		// TODO Auto-generated method stub
		
	}
	
}