package com.base.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AtomicTest {

	private Integer num = 0;

	private Lock lock = new ReentrantLock();

	private AtomicInteger atNum = new AtomicInteger(0);

	public static void main(String[] args) throws InterruptedException {
		new AtomicTest().show();
	}

	private void show() throws InterruptedException {
		for (int i = 0; i < 5; i++) {
//			new Thread(new AtomicSafe(), "th" + i).start();
//			new Thread(new Unsafe(),"unsafe_th"+i).start();
//			new Thread(new SyncSafe(),"SyncSafe_th"+i).start();
			new Thread(new LockSafe(),"LockSafe_th"+i).start();

		}

	}

	class Unsafe implements Runnable {

		public void run() {
			while (true) {
				if (num >= 10) {
					// 如果达到任务指标 完成某个任务
					break;// 中断线程执行
				}
				System.out.println(
						String.format("thread:%s   num:%s Do some thing", Thread.currentThread().getName(), num));
				num++;
			}
		}
	}

	class SyncSafe implements Runnable {

		public void run() {

			synchronized (num) {

				while (true) {
					if (num >= 10) {
						// 如果达到任务指标 完成某个任务
						break;// 中断线程执行
					}
					System.out.println(
							String.format("thread:%s   num:%s Do some thing", Thread.currentThread().getName(), num));
					num++;
				}
			}

		}
	}

	class LockSafe implements Runnable {

		public void run() {

			lock.lock();

			while (true) {
				if (num >= 10) {
					// 如果达到任务指标 完成某个任务
					break;// 中断线程执行
				}
				System.out.println(
						String.format("thread:%s   num:%s Do some thing", Thread.currentThread().getName(), num));
				num++;
			}
			lock.unlock();

		}
	}

	class AtomicSafe implements Runnable {

		public void run() {

			while (true) {
				if (atNum.get() >= 10) {
					// 如果达到任务指标 完成某个任务
					break;// 中断线程执行
				}
				System.out.println(String.format("thread:%s   num:%s Do some thing", Thread.currentThread().getName(),
						atNum.getAndIncrement()));
			}

		}
	}
}
