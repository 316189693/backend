package com.htjy.cglib;

import org.springframework.cglib.proxy.Enhancer;

public class CglibTest {
    public static void main(String[] args) {
        CglibInterceptor cglibInterceptor = new CglibInterceptor();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Pig.class);
        enhancer.setCallback(cglibInterceptor);

        Pig pig = (Pig) enhancer.create();
        pig.skill();
    }
}
