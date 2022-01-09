package com.hit.logres.phoneproject.provider.mapper;



import com.hit.logres.phoneproject.api.entity.Warehouse;

import java.util.List;

public interface WarehouseMapper {
    void insertWarehouse(Warehouse warehouse) throws Exception;

    void deleteWarehouse(Integer id) throws Exception;

    Warehouse findWarehouseById(Integer id) throws Exception;

    List<Warehouse> getAllWarehouse() throws Exception;

    void updateWarehouse(Warehouse warehouse) throws Exception;

    List<Warehouse> findWarehouseByName(String name) throws Exception;
}
