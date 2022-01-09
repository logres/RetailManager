package com.hit.logres.phoneproject.provider.mapper;

import com.hit.logres.phoneproject.api.entity.Inventory;
import com.hit.logres.phoneproject.api.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartMapper {
    void insertShoppingCart(ShoppingCart shoppingCart) throws Exception;

    void deleteShoppingCart(Integer id) throws Exception;

    ShoppingCart findShoppingCartById(Integer id) throws Exception;

    List<ShoppingCart> getAllShoppingCart() throws Exception;

    void updateShoppingCart(ShoppingCart shoppingCart) throws Exception;

    List<ShoppingCart> findShoppingCartByNetCustomerId(Integer id) throws Exception;
}
