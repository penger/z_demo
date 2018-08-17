package com.base.java8;

public class StaticinInterface {

	public static void main(String[] args) {
		new Runnable() {
			@Override
			public void run() {
				
			}
		};
		
		Runnable runnable  = ()->{
			System.out.println();
		};

		
		Interface1 interface1 = (s) -> System.out.println(" interface1 :"+s );
		
		interface1.method1(""+21);
	}

}
@FunctionalInterface
interface Interface1 {

	void method1(String str);
	
//	void method3(String str,String xstr);
	
	default void log(String str){
		System.out.println("I1 logging::"+str);
	}
	
	static void print(String str){
		System.out.println("Printing "+str);
	}
	
	//trying to override Object method gives compile time error as
	//"A default method cannot override a method from java.lang.Object"
	
//	default String toString(){
//		return "i1";
//	}	
}

@FunctionalInterface
interface Interface2 {

	void method2();
	
	default void log(String str){
		System.out.println("I2 logging::"+str);
	}

}

class MyClass implements Interface1, Interface2 {

	@Override
	public void method2() {
	}

	@Override
	public void method1(String str) {
	}

	@Override
	public void log(String str) {
		// TODO Auto-generated method stub
		Interface2.super.log(str);
	}

	//MyClass won't compile without having it's own log() implementation
//	@Override
//	public void log(String str){
//		System.out.println("MyClass logging::"+str);
//		Interface1.print("abc");
//	}
	
}