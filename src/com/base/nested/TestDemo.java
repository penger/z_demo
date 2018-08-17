package com.base.nested;

import com.base.nested.Outer.Inner;
import com.base.nested.Outer.Nested;

public class TestDemo {
	public static void main(String[] args) {
		Nested nested = new Outer.Nested();
		Outer outer = new Outer();
		Inner inner = outer.new Inner();
		inner.printText();
	}

}
