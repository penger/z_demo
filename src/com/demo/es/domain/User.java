package com.demo.es.domain;

public class User {

	private String name ;
	private int age;
	private String favourit;
	
	
	public User(String name, int age, String favourit) {
		super();
		this.name = name;
		this.age = age;
		this.favourit = favourit;
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
	public String getFavourit() {
		return favourit;
	}
	public void setFavourit(String favourit) {
		this.favourit = favourit;
	}
	
	

}
