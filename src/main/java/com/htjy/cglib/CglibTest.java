package com.htjy.cglib;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;

public class CglibTest {
    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "com/sun/cglib/");
        CglibInterceptor cglibInterceptor = new CglibInterceptor();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Pig.class);
        enhancer.setCallback(cglibInterceptor);

        Pig pig = (Pig) enhancer.create();
        pig.skill();
    }
}
