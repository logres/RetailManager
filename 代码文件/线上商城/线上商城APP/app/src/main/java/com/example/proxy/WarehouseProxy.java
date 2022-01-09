package com.example.proxy;

import com.example.api.Warehouse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.util.List;

public class WarehouseProxy {
    private static String baseUrl = NetTool.basebaseurl+"/Warehouse";
    private static Gson gson = new Gson();


    public static List<Warehouse> getAll(){
        try{
            String res = NetTool.get(baseUrl+"/getAll");
            return gson.fromJson(res,new TypeToken<List<Warehouse>>() {
            }.getType());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Integer insert(Warehouse warehouse){
        try{
            String res = NetTool.post(baseUrl+"/insert",gson.toJson(warehouse));
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

    public static Warehouse findById(Integer id){
        try{
            String res = NetTool.post(baseUrl+"/findById",gson.toJson(id));
            return gson.fromJson(res,Warehouse.class);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean update(Warehouse warehouse){
        try{
            String res = NetTool.post(baseUrl+"/update",gson.toJson(warehouse));
            return gson.fromJson(res,Boolean.class);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public static List<Warehouse> findByName(String name){
        try{
            String res = NetTool.post(baseUrl+"/findByName",gson.toJson(name));
            return gson.fromJson(res,new TypeToken<List<Warehouse>>() {
            }.getType());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
