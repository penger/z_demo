package com.happpy.util;

public class B {
	public static void main(String[] args) {
		String s = Rsource2String.getS("z");
		System.out.println(s);
		String[] split = s.split(" ");
		for (String ss : split) {
			if(ss.contains("file=")) {
				System.out.println(ss);
				String a = ss.replaceAll("\"", "").replaceAll("file=", "");
				System.out.println(a);
				a.split("\\.");
				String[] X = a.split(".");
				System.out.println(X[X.length-2]);
			}
			
		}
	}
}
