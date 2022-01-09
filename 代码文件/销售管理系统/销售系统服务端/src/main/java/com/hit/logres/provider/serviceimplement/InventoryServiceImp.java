package com.hit.logres.provider.serviceimplement;

import com.hit.logres.api.entity.Inventory;
import com.hit.logres.provider.mapper.InventoryMapper;
import com.hit.logres.api.service.InventoryService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@org.springframework.stereotype.Service("InventoryService")
@DubboService
public class InventoryServiceImp implements InventoryService {

    @Autowired
    InventoryMapper inventoryMapper;

    @Override
    public List<Inventory> getAllInventory() {
        try {
            return inventoryMapper.getAllInventory();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insertInventory(Inventory user) {
        try {
            inventoryMapper.insertInventory(user);
            return user.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Boolean deleteInventory(Integer id) {
        try {
            inventoryMapper.deleteInventory(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Inventory findInventoryById(Integer id) {
        try {
            return inventoryMapper.findInventoryById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean updateInventory(Inventory customer) {
        try {
            inventoryMapper.updateInventory(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Inventory> findInventoryByWarehouseId(Integer id) {
        try {
            return inventoryMapper.findInventoryByWarehouseId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
