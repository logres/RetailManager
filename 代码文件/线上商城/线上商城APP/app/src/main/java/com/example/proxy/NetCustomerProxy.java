package com.example.proxy;

import com.example.api.NetCustomer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.util.List;

public class NetCustomerProxy {
    private static String baseUrl = NetTool.basebaseurl+"/NetCustomer";
    private static Gson gson = new Gson();


    public static List<NetCustomer> getAll(){
        try{
            String res = NetTool.get(baseUrl+"/getAll");
            return gson.fromJson(res,new TypeToken<List<NetCustomer>>() {
            }.getType());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Integer insert(NetCustomer netCustomer){
        try{
            String res = NetTool.post(baseUrl+"/insert",gson.toJson(netCustomer));
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

    public static NetCustomer findById(Integer id){
        try{
            String res = NetTool.post(baseUrl+"/findById",gson.toJson(id));
            return gson.fromJson(res,NetCustomer.class);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean update(NetCustomer netCustomer){
        try{
            String res = NetTool.post(baseUrl+"/update",gson.toJson(netCustomer));
            return gson.fromJson(res,Boolean.class);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public static List<NetCustomer> findByUsername(String username){
        try{
            String res = NetTool.post(baseUrl+"/findByUsername",gson.toJson(username));
            return gson.fromJson(res,new TypeToken<List<NetCustomer>>() {
            }.getType());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
