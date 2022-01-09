package com.hit.logres.provider.serviceimplement;

import com.hit.logres.api.entity.Purchase;
import com.hit.logres.provider.mapper.PurchaseMapper;
import com.hit.logres.api.service.PurchaseService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@org.springframework.stereotype.Service("PurchaseService")
@DubboService
public class PurchaseServiceImp implements PurchaseService {

    @Autowired
    PurchaseMapper purchaseMapper;

    @Override
    public List<Purchase> getAllPurchase() {
        try {
            return purchaseMapper.getAllPurchase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insertPurchase(Purchase user) {
        try {
            purchaseMapper.insertPurchase(user);
            return user.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Boolean deletePurchase(Integer id) {
        try {
            purchaseMapper.deletePurchase(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Purchase findPurchaseById(Integer id) {
        try {
            return purchaseMapper.findPurchaseById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean updatePurchase(Purchase customer) {
        try {
            purchaseMapper.updatePurchase(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
