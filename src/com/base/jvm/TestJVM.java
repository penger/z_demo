package com.base.jvm;

import java.util.ArrayList;

public class TestJVM {

	public static void main(String[] args) {
		
		
		ArrayList<Byte[]> arrayList = new ArrayList<>();
		for(int i=0;i<1000;i++) {
			Byte[] name = new Byte[10*1024*1024];
			arrayList.add(name);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

}
