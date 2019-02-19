package com.htjy.cglib;

import org.springframework.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

public class CglibFilter implements CallbackFilter {
    @Override
    public int accept(Method method) {
        if ("sound".equals(method.getName())) {
            return 0;
        }
        return 1;
    }
}
