package com.base.thread;

import com.base.ThreadDemo1;

public class DeadLockTest {

	public static void main(String[] args) throws InterruptedException {
		Object o1 = new Object();
		Object o2 = new Object();
		Object o3 = new Object();
		
		Thread t1  =new Thread(new SyncThread(o1, o2),"thread1 ");
		Thread t2  =new Thread(new SyncThread(o2, o1),"thread2 ");
//		Thread t3  =new Thread(new SyncThread(o3, o1),"thread3 ");

		t1.start();
		Thread.sleep(3000);
		t2.start();
		Thread.sleep(5000);
//		t3.start();
		
		
	}

}

class SyncThread implements Runnable{
	
	private Object o1;
	private Object o2;
	
	public SyncThread(Object oo1 , Object oo2) {
		this.o1 = oo1;
		this.o2 = oo2;
	}
	
	@Override
	public void run() {

		System.out.println(Thread.currentThread().getName()+" enter");
		
		synchronized (o1) {
			System.out.println("work with "+o1);
			work();
			synchronized (o2) {
				System.out.println("work with "+o2);
				work();
			}
			System.out.println("no use "+o2);
		}
		System.out.println(" no use o1"+o1);
	}
	
	private void work() {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}