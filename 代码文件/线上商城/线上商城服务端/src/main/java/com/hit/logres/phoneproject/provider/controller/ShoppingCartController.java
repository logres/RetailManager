package com.hit.logres.phoneproject.provider.controller;

import com.hit.logres.phoneproject.api.entity.ShoppingCart;
import com.hit.logres.phoneproject.provider.mapper.ShoppingCartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/ShoppingCart")
public class ShoppingCartController {

    @Autowired
    ShoppingCartMapper shoppingCartMapper;

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    Integer insertShoppingCart(@RequestBody ShoppingCart shoppingCart){
        try{
            shoppingCartMapper.insertShoppingCart(shoppingCart);
            return shoppingCart.getId();
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    Boolean deleteShoppingCart(@RequestBody Integer id){
        try{
            shoppingCartMapper.deleteShoppingCart(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    ShoppingCart findShoppingCartById(@RequestBody Integer id){
        try{
            return shoppingCartMapper.findShoppingCartById(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    List<ShoppingCart> getAllShoppingCart(){
        try{
            return shoppingCartMapper.getAllShoppingCart();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    Boolean updateShoppingCart(@RequestBody ShoppingCart shoppingCart){
        try{
            shoppingCartMapper.updateShoppingCart(shoppingCart);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/findByNetCustomerId",method = RequestMethod.POST)
    List<ShoppingCart> findShoppingCartByNetCustomerId(@RequestBody Integer id){
        try{
            return shoppingCartMapper.findShoppingCartByNetCustomerId(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
