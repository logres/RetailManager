package com.example.proxy;

import com.example.api.ShoppingCartLine;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class ShoppingCartLineProxy {
    private static String baseUrl = NetTool.basebaseurl+"/ShoppingCartLine";
    private static Gson gson = new Gson();


    public static List<ShoppingCartLine> getAll(){
        try{
            String res = NetTool.get(baseUrl+"/getAll");
            return gson.fromJson(res,new TypeToken<List<ShoppingCartLine>>() {
            }.getType());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Integer insert(ShoppingCartLine shoppingCartLine){
        try{
            String res = NetTool.post(baseUrl+"/insert",gson.toJson(shoppingCartLine));
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

    public static ShoppingCartLine findById(Integer id){
        try{
            String res = NetTool.post(baseUrl+"/findById",gson.toJson(id));
            return gson.fromJson(res,ShoppingCartLine.class);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean update(ShoppingCartLine shoppingCartLine){
        try{
            String res = NetTool.post(baseUrl+"/update",gson.toJson(shoppingCartLine));
            return gson.fromJson(res,Boolean.class);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public static List<ShoppingCartLine> findByShoppingCartId(Integer id){
        try{
            String res = NetTool.post(baseUrl+"/findByShoppingCartId",gson.toJson(id));
            return gson.fromJson(res,new TypeToken<List<ShoppingCartLine>>() {
            }.getType());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
