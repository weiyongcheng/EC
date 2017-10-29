package com.wei.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2017/10/27.
 */

@Target(ElementType.TYPE)   //在类上使用的注解
@Retention(RetentionPolicy.SOURCE)  //源码阶段
public @interface AppRegisterGenerator {

    String packageName();

    Class<?> registerTemplate();
}
