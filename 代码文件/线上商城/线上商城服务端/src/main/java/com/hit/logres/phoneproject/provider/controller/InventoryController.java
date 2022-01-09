package com.hit.logres.phoneproject.provider.controller;


import com.hit.logres.phoneproject.api.entity.Inventory;
import com.hit.logres.phoneproject.provider.mapper.InventoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/Inventory")
public class InventoryController {
    @Autowired
    InventoryMapper inventoryMapper;

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public List<Inventory> getAllInventory() {
        try {
            return inventoryMapper.getAllInventory();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public int insertInventory(@RequestBody Inventory user) {
        try {
            inventoryMapper.insertInventory(user);
            return user.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Boolean deleteInventory(@RequestBody Integer id) {
        try {
            inventoryMapper.deleteInventory(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public Inventory findInventoryById(@RequestBody Integer id) {
        try {
            return inventoryMapper.findInventoryById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Boolean updateInventory(@RequestBody Inventory customer) {
        try {
            inventoryMapper.updateInventory(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/findByWarehouseId",method = RequestMethod.POST)
    public List<Inventory> findInventoryByWarehouseId(@RequestBody Integer id) {
        try {
            return inventoryMapper.findInventoryByWarehouseId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
