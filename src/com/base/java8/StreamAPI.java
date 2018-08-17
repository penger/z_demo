package com.base.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamAPI {

	public static void main(String[] args) {
		List<Integer> myList = new ArrayList<>();
		for(int i=0; i<100; i++) myList.add(i);
		
		Stream<Integer> sequentialStream = myList.stream();
		Stream<Integer> parallelStream = myList.parallelStream();
		
		parallelStream.filter(new Predicate<Integer>() {
			@Override
			public boolean test(Integer t) {
				if(t>90) {
					return true;
				}
				return false;
			}
			
		}).forEach(x -> System.out.println(x));

	}

}
