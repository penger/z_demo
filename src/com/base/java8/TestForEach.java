package com.base.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TestForEach {

	public static void main(String[] args) {
		List<Integer> arrayList = new ArrayList<>();
		for(int i =0 ;i<100 ;i++ ) arrayList.add(i);
		
		arrayList.forEach(new Consumer<Integer>() {
			@Override
			public void accept(Integer t) {
				System.out.println(t);
				
			};
		});
	}

}
