package com.htjy.controller;

import com.htjy.entity.User;
import com.htjy.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource(name = "userServiceImpl")
    private UserService userService;

    @RequestMapping("/showUser")
    @ResponseBody
    public User toIndex(HttpServletRequest request, Model model){
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = this.userService.getUserById(userId);
        return user;
    }


    @RequestMapping("/updateUser")
    @ResponseBody
    public User update(HttpServletRequest request, Model model){
        int userId = 1;
        User user = new User();
        user.setId(Integer.parseInt(request.getParameter("id")));
        user.setScore(Integer.parseInt(request.getParameter("score")));
        user.setStatus(Integer.parseInt(request.getParameter("status")));
        return this.userService.updateUser(user);
    }
    @RequestMapping(path = "/createUser", method = RequestMethod.POST)
    @ResponseBody
    public User create(@RequestBody User user, Model model){
        return this.userService.updateUser(user);
    }
}
