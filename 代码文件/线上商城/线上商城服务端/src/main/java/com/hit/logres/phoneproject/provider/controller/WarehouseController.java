package com.hit.logres.phoneproject.provider.controller;
import com.hit.logres.phoneproject.api.entity.Warehouse;
import com.hit.logres.phoneproject.provider.mapper.WarehouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/Warehouse")
public class WarehouseController {
    @Autowired
    WarehouseMapper warehouseMapper;

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public List<Warehouse> getAllWarehouse() {
        try {
            return warehouseMapper.getAllWarehouse();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public int insertWarehouse(@RequestBody Warehouse user) {
        try {
            warehouseMapper.insertWarehouse(user);
            return user.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Boolean deleteWarehouse(@RequestBody Integer id) {
        try {
            warehouseMapper.deleteWarehouse(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public Warehouse findWarehouseById(@RequestBody Integer id) {
        try {
            return warehouseMapper.findWarehouseById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Boolean updateWarehouse(@RequestBody Warehouse customer) {
        try {
            warehouseMapper.updateWarehouse(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/findByName",method = RequestMethod.POST)
    public List<Warehouse> findWarehouseByName(@RequestBody String name) {
        try {
            return warehouseMapper.findWarehouseByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
