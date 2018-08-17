package com.base.abstrac;

//设计模式中的模板模式
//

public class NewFunction extends MyAbstractClass {

	@Override
	public void abstractMethod() {
		System.out.println("my fuction !~!!");

	}

	public static void main(String[] args) {
		new NewFunction().run();

	}

}
