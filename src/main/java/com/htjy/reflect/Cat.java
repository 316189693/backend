package com.htjy.reflect;

import org.springframework.stereotype.Component;

/**
 * Created By willz
 * Date : 12/26/2019
 * Time: 10:01 AM
 */
@Component
public class Cat implements Pet {
    @Override
    public void skill() {
        System.out.println("catch mouse");
    }
}
