package com.base.refect;


public class User {
	
	
	private String name ;
	private int age ;
	
	
	
	
	public User(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String happy() {
		return "name :"+name +"       age : "+age;
	}
	
	
	private void changeInfo() {
		this.name = name + "gongpeng";
		this.age = age + 1000;
	}
	
	
	private void invoke() {
		this.name = "hkkkk";
		this.age =  1000;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
