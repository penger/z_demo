package com.base.refect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Play {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		User user = new User("wangjianlin", 10);
		System.out.println(user.happy());
		Method declaredMethod = User.class.getDeclaredMethod("invoke", null);
		declaredMethod.setAccessible(true);
		declaredMethod.invoke(user, null);
		System.out.println(user.happy());
		
		
	}

}
