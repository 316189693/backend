package com.htjy.jdk;

public class Cat implements IAnimal {
    @Override
    public void sound() {
        System.out.println("miao miao....");
    }

    @Override
    public void skill() {
        System.out.println("catch mouse....");
    }
}
