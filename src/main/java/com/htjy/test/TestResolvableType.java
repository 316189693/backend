package com.htjy.test;

import com.htjy.entity.User;
import com.htjy.ioc.Cat;
import org.springframework.core.MethodParameter;
import org.springframework.core.ResolvableType;

import java.lang.reflect.Method;


public class TestResolvableType {
    public static void main(String[] args) {
        Method[] methods = TestResolvableType.class.getMethods();
        for (Method method:methods
             ) {
            if (method.getName().equals("getUser")) {
                MethodParameter methodParameter = new MethodParameter(method, 1);
               ResolvableType resolvableType = ResolvableType.forMethodParameter(methodParameter);
                System.out.println(resolvableType.getType().getTypeName());
            }

        }
    }

    public User getUser(User user, Cat cat, String name) {
        return user;
    }
}
