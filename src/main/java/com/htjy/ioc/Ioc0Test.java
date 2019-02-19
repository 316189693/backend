package com.htjy.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Ioc0Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("src/main/java/com/htjy/ioc/Bean.xml");
        IAnimal animal = (IAnimal) applicationContext.getBean("animal");
        animal.skill();
        animal.friendSkill();
    }
}
