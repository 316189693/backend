package com.htjy.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.htjy.aop")
@EnableAspectJAutoProxy//开启AspectJ注解
public class CustomAopConfigurer {
}
