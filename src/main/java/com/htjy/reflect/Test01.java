package com.htjy.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created By willz
 * Date : 12/26/2019
 * Time: 2:20 PM
 */
public class Test01 {
    public static void main(String[] args) {


        Field[] fields = ParameterizeBran.class.getDeclaredFields();
        showFieldInfo(fields);
       Method m = ParameterizeBran.class.getEnclosingMethod();
        System.out.println(" ");
    }

    public static void showMethodInfo(Method[] methods) {
        for (Method m: methods) {
            Type t = m.getGenericReturnType();
            Type[] d = m.getGenericParameterTypes();
            System.out.println("s");
        }
    }

    public static void showFieldInfo(Field[] fields) {
        for (Field f: fields) {
            System.out.println(f.getName()+":"+(f.getGenericType() instanceof ParameterizedType));
            if (f.getGenericType() instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) f.getGenericType();
                System.out.println("Var:"+parameterizedType.getTypeName());
                Type rawType = parameterizedType.getRawType();
                if (rawType != null) {
                    System.out.println("rawType:"+rawType.getTypeName());
                }
                Type ownType = parameterizedType.getOwnerType();
                if (ownType != null) {
                    System.out.println("ownType:"+ownType.getTypeName());
                } else {
                    System.out.println("ownType:null");
                }
                Type[] types = parameterizedType.getActualTypeArguments();
                for (Type t: types) {
                    System.out.println("Type:"+t.getTypeName());
                }

            } else if (f.getGenericType() instanceof Class) {
                Class clazz = (Class<?>) f.getGenericType();
                System.out.println("class:"+clazz.getName());
                System.out.println("isArray:"+clazz.isArray());
            }
            System.out.println("");
        }
    }

}
