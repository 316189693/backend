package com.htjy.ASM;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;

/**
 * Created By willz
 * Date : 12/26/2019
 * Time: 11:37 AM
 */
public class Test {
    public static void main(String[] args) {
        ClassPool classPool = ClassPool.getDefault();
        CtClass cc = classPool.makeClass("Dog");
    }
}
