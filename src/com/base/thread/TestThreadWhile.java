package com.base.thread;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TestThreadWhile {

	public static void main(String[] args) {
		while(!Thread.currentThread().isInterrupted()) {
			System.out.println("runing ...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(new SimpleDateFormat("HH:mm:ss").format(new Date()));
		}

	}

}
