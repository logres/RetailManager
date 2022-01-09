package com.hit.logres.provider.serviceimplement;

import com.hit.logres.api.entity.Warehouse;
import com.hit.logres.provider.mapper.WarehouseMapper;
import com.hit.logres.api.service.WarehouseService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service("WarehouseService")
@DubboService
public class WarehouseServiceImp implements WarehouseService {

    @Autowired
    WarehouseMapper warehouseMapper;

    @Override
    public List<Warehouse> getAllWarehouse() {
        try {
            return warehouseMapper.getAllWarehouse();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insertWarehouse(Warehouse user) {
        try {
            warehouseMapper.insertWarehouse(user);
            return user.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Boolean deleteWarehouse(Integer id) {
        try {
            warehouseMapper.deleteWarehouse(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Warehouse findWarehouseById(Integer id) {
        try {
            return warehouseMapper.findWarehouseById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean updateWarehouse(Warehouse customer) {
        try {
            warehouseMapper.updateWarehouse(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Warehouse> findWarehouseByName(String name) {
        try {
            return warehouseMapper.findWarehouseByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
