package com.base.ann;

import java.awt.event.ActionListener;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)  //定义作用在字段上
@Documented
public @interface ActionListenerAnno {
	Class<? extends ActionListener> listener();
}
