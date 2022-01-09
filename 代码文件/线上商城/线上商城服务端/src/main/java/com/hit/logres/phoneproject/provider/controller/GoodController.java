package com.hit.logres.phoneproject.provider.controller;


import com.hit.logres.phoneproject.api.entity.Good;
import com.hit.logres.phoneproject.provider.mapper.GoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/Good")
public class GoodController {
    @Autowired
    GoodMapper goodMapper;

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public List<Good> getAllGood() {
        try {
            return goodMapper.getAllGood();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public int insertGood(@RequestBody Good user) {
        try {
            goodMapper.insertGood(user);
            return user.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Boolean deleteGood(@RequestBody Integer id) {
        try {
            goodMapper.deleteGood(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public Good findGoodById(@RequestBody Integer id) {
        try {
            return goodMapper.findGoodById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Boolean updateGood(@RequestBody Good customer) {
        try {
            goodMapper.updateGood(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/findByName",method = RequestMethod.POST)
    public List<Good> findGoodByName(@RequestBody String name) {
        try {
            return goodMapper.findGoodByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
