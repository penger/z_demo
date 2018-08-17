package com.base;

import java.util.HashMap;
import java.util.Map;


public class CacheDemo {
	
	public static Map<Integer,Integer> cache = new HashMap<>();

	public static void main(String[] args) {
		for(int j=0;j<6;j++) {
			System.out.println("-----------------第"+j+"次");
			for(int i=0;i<10;i++) {
				System.out.println(fib(i));
			}
		}

	}
	
	static int fib(int i) {
		System.out.println("求值"+i);
		if(i==0 || i==1) {
			return 1;
		}
		return cache.computeIfAbsent(i, (key)->(fib(i-1)+fib(i-2)));
	}

}
