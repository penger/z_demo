package com.base.thread.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SimpleLockTest {

	public static void main(String[] args) {
		Resouce resouce = new Resouce();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true ) {
					resouce.read();
				}
				
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					resouce.add();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}).start();
	}

}

// 测试锁操作
class Resouce {
	private int mynum = 0;
//	private Lock lock = new ReentrantLock();
	private ReadWriteLock  lock = new ReentrantReadWriteLock(false);
	

	public void add() {
		try {
			lock.writeLock().lock();
			for (int i = 0; i < 5; i++) {
				System.out.println("into write to write");
				mynum++;
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			lock.writeLock().unlock();
		}
	}

	public void read() {
		lock.readLock().lock();
		try {
			Thread.sleep(1000 * 1);
			System.out.println("current value is :" + mynum);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lock.readLock().unlock();
	}

}