package com.example.proxy;

import com.example.api.ShoppingCart;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class ShoppingCartProxy {
    private static String baseUrl = NetTool.basebaseurl+"/ShoppingCart";
    private static Gson gson = new Gson();


    public static List<ShoppingCart> getAll(){
        try{
            String res = NetTool.get(baseUrl+"/getAll");
            return gson.fromJson(res,new TypeToken<List<ShoppingCart>>() {
            }.getType());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Integer insert(ShoppingCart shoppingCart){
        try{
            String res = NetTool.post(baseUrl+"/insert",gson.toJson(shoppingCart));
            return gson.fromJson(res,Integer.class);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static Boolean delete(Integer id){
        try{
            String res = NetTool.post(baseUrl+"/delete",gson.toJson(id));
            return gson.fromJson(res,Boolean.class);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static ShoppingCart findById(Integer id){
        try{
            String res = NetTool.post(baseUrl+"/findById",gson.toJson(id));
            return gson.fromJson(res,ShoppingCart.class);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean update(ShoppingCart shoppingCart){
        try{
            String res = NetTool.post(baseUrl+"/update",gson.toJson(shoppingCart));
            return gson.fromJson(res,Boolean.class);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static List<ShoppingCart> findByNetCustomerId(Integer id){
        try{
            String res = NetTool.post(baseUrl+"/findByNetCustomerId",gson.toJson(id));
            return gson.fromJson(res,new TypeToken<List<ShoppingCart>>() {
            }.getType());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
