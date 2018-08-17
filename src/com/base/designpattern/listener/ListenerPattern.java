package com.base.designpattern.listener;

import java.util.ArrayList;
import java.util.List;


public class ListenerPattern {

	public static void main(String[] args) {
		Child child = new Child("xiaoming");
		child.addListener(new RemindListener() {
			@Override
			public void remindThings() {
				System.out.println("执行了方法");
				
			}
		});
		child.eat();

	}

}

class Child {
	private String name;
	private List<RemindListener> listenerlist;
	
	public Child(String name) {
		super();
		this.name = name;
		listenerlist = new ArrayList<RemindListener>();
	}

	public void eat() {
		if(!listenerlist.isEmpty()) {
			System.out.println(name+ "is eating ");
		}
	}
	
	public void addListener(RemindListener otherlistener) {
		this.listenerlist.add(otherlistener);
	}
}

class EatListener implements RemindListener{

	@Override
	public void remindThings() {
		System.out.println("提醒洗手");
		
	}
	
}


interface RemindListener {
	public void remindThings();
}