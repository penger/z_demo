package com.base.enumm;

public class TestEnum {

	public static void main(String[] args) {
		Level[] values = Level.values();
		for (Level level : values) {
			System.out.println(level.ordinal());
		}
		
		
		System.out.println(Level.HIGH);
	}

}
