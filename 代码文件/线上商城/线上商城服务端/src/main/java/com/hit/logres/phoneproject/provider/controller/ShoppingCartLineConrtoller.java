package com.hit.logres.phoneproject.provider.controller;


import com.hit.logres.phoneproject.api.entity.ShoppingCartLine;
import com.hit.logres.phoneproject.provider.mapper.ShoppingCartLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/ShoppingCartLine")
public class ShoppingCartLineConrtoller {

    @Autowired
    ShoppingCartLineMapper shoppingCartLineMapper;

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    void insertShoppingCartLine(@RequestBody ShoppingCartLine shoppingCartLine){
        try{
            shoppingCartLineMapper.insertShoppingCartLine(shoppingCartLine);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    void deleteShoppingCartLine(@RequestBody Integer id){
        try{
            shoppingCartLineMapper.deleteShoppingCartLine(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    ShoppingCartLine findShoppingCartLineById(@RequestBody Integer id){
        try{
            return shoppingCartLineMapper.findShoppingCartLineById(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    List<ShoppingCartLine> getAllShoppingCartLine(){
        try{
            return shoppingCartLineMapper.getAllShoppingCartLine();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @RequestMapping(value = "/update",method = RequestMethod.POST)
    void updateShoppingCartLine(@RequestBody ShoppingCartLine shoppingCartLine){
        try{
            shoppingCartLineMapper.updateShoppingCartLine(shoppingCartLine);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/findByShoppingCartId",method = RequestMethod.POST)
    List<ShoppingCartLine> findShoppingCartLineByShoppingCartId(@RequestBody Integer shoppingcartId){
        try{
            return shoppingCartLineMapper.findShoppingCartLineByShoppingCartId(shoppingcartId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
