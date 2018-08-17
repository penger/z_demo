package com.base.ann;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
	String value() default "";
	String name();
	int age();
	String[] newNames();
}
