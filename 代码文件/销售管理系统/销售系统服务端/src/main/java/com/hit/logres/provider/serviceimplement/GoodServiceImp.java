package com.hit.logres.provider.serviceimplement;

import com.hit.logres.api.entity.Good;
import com.hit.logres.provider.mapper.GoodMapper;
import com.hit.logres.api.service.GoodService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@org.springframework.stereotype.Service("GoodService")
@DubboService
public class GoodServiceImp implements GoodService {

    @Autowired
    GoodMapper goodMapper;

    @Override
    public List<Good> getAllGood() {
        try {
            return goodMapper.getAllGood();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insertGood(Good user) {
        try {
            goodMapper.insertGood(user);
            return user.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Boolean deleteGood(Integer id) {
        try {
            goodMapper.deleteGood(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Good findGoodById(Integer id) {
        try {
            return goodMapper.findGoodById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean updateGood(Good customer) {
        try {
            goodMapper.updateGood(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Good> findGoodByName(String name) {
        try {
            return goodMapper.findGoodByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
