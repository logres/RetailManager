package com.hit.logres.provider.serviceimplement;

import com.hit.logres.api.entity.User;
import com.hit.logres.provider.mapper.UserMapper;
import com.hit.logres.api.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@org.springframework.stereotype.Service("UserService")
@DubboService
public class UserServiceImp implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> getAllUser() {
        try {
            return userMapper.getAllUser();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insertUser(User user) {
        try {
            userMapper.insertUser(user);
            return user.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Boolean deleteUser(Integer id) {
        try {
           userMapper.deleteUser(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User findUserById(Integer id) {
        try {
            return userMapper.findUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean updateUser(User customer) {
        try {
            userMapper.updateUser(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> findUserByName(String name) {
        try {
            return userMapper.findUserByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}