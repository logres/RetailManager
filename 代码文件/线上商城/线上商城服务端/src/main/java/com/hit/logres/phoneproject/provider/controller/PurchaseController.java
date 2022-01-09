package com.hit.logres.phoneproject.provider.controller;

import com.hit.logres.phoneproject.api.entity.Purchase;
import com.hit.logres.phoneproject.provider.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/Purchase")
public class PurchaseController {
    @Autowired
    PurchaseMapper purchaseMapper;

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public List<Purchase> getAllPurchase() {
        try {
            return purchaseMapper.getAllPurchase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public int insertPurchase(@RequestBody Purchase user) {
        try {
            purchaseMapper.insertPurchase(user);
            return user.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Boolean deletePurchase(@RequestBody Integer id) {
        try {
            purchaseMapper.deletePurchase(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public Purchase findPurchaseById(@RequestBody Integer id) {
        try {
            return purchaseMapper.findPurchaseById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Boolean updatePurchase(@RequestBody Purchase customer) {
        try {
            purchaseMapper.updatePurchase(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
