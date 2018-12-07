package com.htjy.service;

import com.htjy.entity.User;

public interface UserService {
    public User getUserById(int userId);

    boolean addUser(User record);

    User updateUser(User user);
}
