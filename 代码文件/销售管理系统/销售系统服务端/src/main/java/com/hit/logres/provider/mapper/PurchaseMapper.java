package com.hit.logres.provider.mapper;

import com.hit.logres.api.entity.Purchase;

import java.util.List;

public interface PurchaseMapper {
    void insertPurchase(Purchase purchase) throws Exception;

    void deletePurchase(Integer id) throws Exception;

    Purchase findPurchaseById(Integer id) throws Exception;

    List<Purchase> getAllPurchase() throws Exception;

    void updatePurchase(Purchase purchase) throws Exception;
}
