package com.base;

public class ThreadDemo1 {

	public static void main(String[] args) {
		Mantou mantou = new Mantou();
		for(int i=1;i<40;i++){
//			System.out.println("--------------");
			new Worker("worker---"+i, mantou).start();
		}
	}

	
}

class Worker extends Thread{
	private String name;
	private int count = 0;
	private Mantou mantou;
	private static int MAX=3;

	
	public Worker(String name, Mantou mantou) {
		super();
		this.name = name;
		this.mantou = mantou;
	}


	@Override
	public void run() {
		while (true) {
			if(count==MAX){
				return;
			}
			int one = mantou.getOne();
			if(one==-1){
				return;
			}else {
				count++;
				System.out.println(name +"     mantou:"+one+" count:"+count);
			}
			
		}
			
	}
	
}

class Mantou {
	int count=100;
	public int getOne() {
		if(count>0){
			int temp = count;
			count--;
			return temp;
					
		}else {
			return -1;
		}
	}
	
	
}