package com.base.abstrac;

import sun.security.x509.URIName;

public abstract class MyAbstractClass {
	
	public void stepBefore() {
		System.out.println("before");
	}
	
	public  abstract void abstractMethod();
	
	public void stepAfter() {
		
		System.out.println("after");
	}
	
	public void run() {
		stepBefore();
		abstractMethod();
		stepAfter();
	}

}
