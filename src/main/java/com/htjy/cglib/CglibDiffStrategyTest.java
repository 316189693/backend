package com.htjy.cglib;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;

public class CglibDiffStrategyTest {
    public static void main(String[] args) {
        CglibInterceptor cglibInterceptor = new CglibInterceptor();
        CglibAnthoerInterceptor cglibAnthoerInterceptor = new CglibAnthoerInterceptor();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Pig.class);
        //Callback数组中NoOp.INSTANCE，这表示一个空Callback，即如果不想对某个方法进行拦截，可以在DaoFilter中返回2，具体效果可以自己尝试一下。
        enhancer.setCallbacks(new Callback[]{cglibInterceptor, cglibAnthoerInterceptor, NoOp.INSTANCE});
        enhancer.setCallbackFilter(new CglibFilter());
        //在构造函数中调用方法时，不拦截
        enhancer.setInterceptDuringConstruction(false);



        Pig pig = (Pig) enhancer.create();
        pig.skill();
        pig.sound();
    }
}
