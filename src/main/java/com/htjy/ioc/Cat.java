package com.htjy.ioc;

public class Cat implements IAnimal {
    private IAnimal friend;

    public Cat(){}
    public Cat(IAnimal friend) {
        this.friend = friend;
    }
    @Override
    public void sound() {
        System.out.println("miao miao....");
    }

    @Override
    public void skill() {
        System.out.println("catch mouse....");
    }


    public void friendSound() {
        this.friend.sound();
    }

    public void friendSkill() {
        this.friend.skill();
    }

    public IAnimal getFriend() {
        return friend;
    }

    public void setFriend(IAnimal friend) {
        this.friend = friend;
    }
}
