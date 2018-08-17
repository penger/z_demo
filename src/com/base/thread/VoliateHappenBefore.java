package com.base.thread;


public class VoliateHappenBefore {

	protected static volatile boolean need = true;

	public static void main(String[] args) throws InterruptedException {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(need) {
//					System.out.println("-");
				}
			}
		}).start();
		Thread.sleep(1000);
		need = false;
	}
	


}
