package com.hit.logres.provider.mapper;

import com.hit.logres.api.entity.Inventory;

import java.util.List;

public interface InventoryMapper {
    void insertInventory(Inventory inventory) throws Exception;

    void deleteInventory(Integer id) throws Exception;

    Inventory findInventoryById(Integer id) throws Exception;

    List<Inventory> getAllInventory() throws Exception;

    void updateInventory(Inventory inventory) throws Exception;

    List<Inventory> findInventoryByWarehouseId(Integer id);
}
