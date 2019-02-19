package com.htjy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocationHandler<T> implements InvocationHandler {


    private T target;
    public MyInvocationHandler(T target){
           this.target = target;
    }

    public  T bind(){
        if (target.getClass().getInterfaces().length <= 0) {
            throw new IllegalArgumentException("obj no implements interfaces, cannot use proxy");
        }
       return (T) Proxy.newProxyInstance(this.target.getClass().getClassLoader(), this.target.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("start.......");
        method.invoke(this.target, args);
        System.out.println("end.......");
        return null;
    }
}
