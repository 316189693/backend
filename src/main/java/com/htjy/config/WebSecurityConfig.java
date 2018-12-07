package com.htjy.config;


import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.*;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {


    public void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests().antMatchers("/user/**").permitAll()
               .and().formLogin().successForwardUrl("/user/findone");
    }
}