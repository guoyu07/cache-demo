package com.shimh.extend.easycache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value={ ElementType.METHOD})//类型上
@Retention(RetentionPolicy.RUNTIME)//作用域
@Inherited
public @interface CacheEvict {
	
	String cacheName() default "";
	
	String[] keys() default {};
	
}
