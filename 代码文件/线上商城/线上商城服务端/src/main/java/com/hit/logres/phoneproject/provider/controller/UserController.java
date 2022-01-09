package com.hit.logres.phoneproject.provider.controller;


import com.hit.logres.phoneproject.api.entity.User;
import com.hit.logres.phoneproject.provider.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/User")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public List<User> getAllUser() {
        try {
            return userMapper.getAllUser();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public int insertUser(@RequestBody User user) {
        try {
            userMapper.insertUser(user);
            return user.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Boolean deleteUser(@RequestBody Integer id) {
        try {
            userMapper.deleteUser(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public User findUserById(@RequestBody Integer id) {
        try {
            return userMapper.findUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Boolean updateUser(@RequestBody User customer) {
        try {
            userMapper.updateUser(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/findByName",method = RequestMethod.POST)
    public List<User> findUserByName(@RequestBody String name) {
        try {
            return userMapper.findUserByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
