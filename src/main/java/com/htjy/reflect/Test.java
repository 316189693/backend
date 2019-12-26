package com.htjy.reflect;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;

import java.util.Set;

/**
 * Created By willz
 * Date : 12/25/2019
 * Time: 5:18 PM
 */
public class Test {
    public static void main(String[] args) {
        ClassPathScanningCandidateComponentProvider classPathScanningCandidateComponentProvider = new ClassPathScanningCandidateComponentProvider(true);
        Set<BeanDefinition> beans = classPathScanningCandidateComponentProvider.findCandidateComponents("com.htjy.annotation");
        System.out.println("success");
    }

    private String cry(){
        return "wuwuwu......";
    }
}
