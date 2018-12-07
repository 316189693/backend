package com.htjy.service.impl;

import com.htjy.dao.UserDao;
import com.htjy.entity.User;
import com.htjy.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User getUserById(int userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    @Override
    public boolean addUser(User record) {
        boolean result = false;
        try {
            userDao.insertSelective(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public User updateUser(User user) {
        userDao.updateByPrimaryKey(user);
        return getUserById(user.getId());
    }
}
