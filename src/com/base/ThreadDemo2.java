package com.base;

public class ThreadDemo2 {

	public static void main(String[] args) throws InterruptedException {
		Man man1 = new Man("man1", 1000);
		Man man2 = new Man("man2", 2000);
		Man man3 = new Man("man3", 3000);
		Man man4 = new Man("man4", 4000);
		
		man1.start();
		man2.start();
		man3.start();
		man4.start();
		
		man1.join();
		man2.join();
		man3.join();
		man4.join();
		
		System.out.println("start playing ...");

	}

}

class Man extends Thread{
	
	
	private String name;
	private int time;
	
	
	public Man(String name, int time) {
		super();
		this.name = name;
		this.time = time;
	}


	@Override
	public void run() {
		try {
			System.out.println(name+"start");
			Thread.sleep(time);
			System.out.println(name+"end");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}