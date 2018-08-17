package com.base;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;



public class HappyTest {

	public static void main(String[] args) throws InterruptedException {
//		int[] intArr = {1,2,3,4,5,6,7,8,9};
//		revo(intArr);
		differencebetweenhashmapandhashtable();
	}

	//测试字符串翻转
	private static void revo(int[] intArr) {
		printarr(intArr);
		int length =  intArr.length;
		int middle  = length/2;
		for(int i =0 ;i< middle; i++) {
			int tmp  = intArr[i];
			intArr[i]= intArr[length-i-1];
			intArr[length-i-1] = tmp;
			
		}
		System.out.println("------------------------------------");
		printarr(intArr);
		
	}

	private static void printarr(int[] intArr) {
		for (int i : intArr) {
			System.out.println(i);
		}
	}
	
	
	private static void differencebetweenhashmapandhashtable() throws InterruptedException {
		HashMap<String , String> hashMap = new LinkedHashMap();
		hashMap.put("1", "");
		hashMap.put("2", "");
		hashMap.put("4", "");
		hashMap.put("3", "");
		Thread.sleep(1000*60*10);
		hashMap.forEach((key,value)->System.out.println(key));
//		Hashtable<Object, Object> hashtable = new Hashtable<>();
	}
	

}
