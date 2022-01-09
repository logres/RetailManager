package com.hit.logres.phoneproject.provider.mapper;

import com.hit.logres.phoneproject.api.entity.ShoppingCartLine;

import java.util.List;

public interface ShoppingCartLineMapper {
    void insertShoppingCartLine(ShoppingCartLine shoppingCartLine) throws Exception;

    void deleteShoppingCartLine(Integer id) throws Exception;

    ShoppingCartLine findShoppingCartLineById(Integer id) throws Exception;

    List<ShoppingCartLine> getAllShoppingCartLine() throws Exception;

    void updateShoppingCartLine(ShoppingCartLine shoppingCartLine) throws Exception;

    List<ShoppingCartLine> findShoppingCartLineByShoppingCartId(Integer shoppingcartId) throws Exception;

}
