package com.base.designpattern.template;

import java.io.*;

//可以跑的装置
public class TemplatePattern {
	
	public static void main(String[] args) throws IOException {

        FileInputStream input = new FileInputStream(new File("d://a.txt"));
        InputStreamReader reader = new InputStreamReader(input);

        input.read();
        reader.read();


        int v;
        while((v=input.read())!=-1){
            char i= (char) v;
            System.out.println(i);
        }
        Car car = new Car();
		car.run();
		House house = new House();
		house.run();
	}
	
}

abstract class RunEquipment{
	public void didi() {
		System.out.println("didi");
	}
	public void bangbang() {
		System.out.println("bangbang");
	}
	public void qiaqiaqia() {
		System.out.println("qiaqiaqia");
	}
	public abstract void ownrun();
	
	public void run() {
		ownrun();
		didi();
		bangbang();
		qiaqiaqia();
	}
}

class Car extends RunEquipment{

	@Override
	public void ownrun() {
		System.out.println("car run");
	}
	
}

class House extends RunEquipment{

	@Override
	public void ownrun() {
		System.out.println("house run");
	}
	
}