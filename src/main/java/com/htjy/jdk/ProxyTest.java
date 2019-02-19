package com.htjy.jdk;



public class ProxyTest {
    public static void main(String[] args) {
       // System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");

      IAnimal dog = (IAnimal) new MyInvocationHandler(new Dog()).bind();
      dog.sound();
    //  dog.skill();

      /*  IAnimal cat = (IAnimal) new MyInvocationHandler(new Cat()).bind();
        cat.sound();
        cat.skill();*/
    }
}
