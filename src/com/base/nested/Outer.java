package com.base.nested;

import org.apache.jasper.tagplugins.jstl.core.Out;

public class Outer {
	//静态内部类
	public static class Nested{
		
	}
	
	public class Inner {
		void printText() {
			System.out.println("text");
		}
	}
	
	
	
}
