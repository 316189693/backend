package com.htjy.ioc;

public class Dog implements IAnimal {
    @Override
    public void sound() {
        System.out.println("wang wang....");
    }

    @Override
    public void skill() {
        System.out.println("house keeper...");
    }

    @Override
    public void friendSound() {

    }

    @Override
    public void friendSkill() {

    }
}
