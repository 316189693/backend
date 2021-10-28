package com.htjy.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.htjy.entity.User;

import java.io.File;
import java.io.IOException;

public class ObjectMapperTest {
    public static void main(String[] args) {
       User user = getUser();
        System.out.println(user.getId());
    }
  public static User getUser(){
      ObjectMapper objectMapper = new ObjectMapper();
      User user = null;
      try {
           user =  objectMapper.readValue(new File("./user.json"), User.class);
      } catch (IOException e) {
          e.printStackTrace();
      }
      return user;
  }
    public static void generateUserJson() {
        User user = new User();
        user.setId(10);
        user.setScore(23);
        user.setStatus(45);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("./user.json"), user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
