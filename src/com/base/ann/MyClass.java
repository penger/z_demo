package com.base.ann;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;

@MyAnnotation(name="gongpeng", age = 30, newNames = { "henan","xinyang" })
public class MyClass {
	public static void main(String[] args) {
//		Class clazz  = MyClass.class;
//		Annotation[] annotations = clazz.getAnnotations();
//		for (Annotation annotation : annotations) {
//			MyAnnotation myAnnotation = (MyAnnotation) annotation;
//			System.out.println(myAnnotation.age());
//			System.out.println(myAnnotation.newNames());
//			
//		}
		
		Method[] declaredMethods = HashMap.class.getDeclaredMethods();
		for (Method method : declaredMethods) {
			System.out.println(method.getName());
		}
	}
}
