package com.htjy.jdk;

public class Dog implements IAnimal {
    @Override
    public void sound() {
        System.out.println("wang wang....");
    }

    @Override
    public void skill() {
        System.out.println("house keeper...");
    }
}
