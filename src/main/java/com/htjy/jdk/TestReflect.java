package com.htjy.jdk;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created By willz
 * Date : 2021/3/9
 * Time: 17:48
 */
public class TestReflect {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Class var1 = dog.getClass();
        Type[] var2 = var1.getGenericInterfaces();
        if ((var2 = var1.getGenericInterfaces()) != null) {
            for(int var6 = 0; var6 < var2.length; ++var6) {
                Type[] var3;
                Type var4 = var2[var6];
                ParameterizedType var5 = (ParameterizedType)var4;
                Type var7 =var5.getRawType();
                Type[] var8  = var5.getActualTypeArguments();

            }
        }
    }
}
