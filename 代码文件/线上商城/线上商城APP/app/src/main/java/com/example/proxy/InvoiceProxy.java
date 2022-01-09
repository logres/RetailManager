package com.example.proxy;

import com.example.api.Invoice;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import java.util.List;

public class InvoiceProxy {
    private static String baseUrl = NetTool.basebaseurl+"/Invoice";
    //private static Gson gson = new Gson();
    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();


    public static List<Invoice> getAll(){
        try{
            String res = NetTool.get(baseUrl+"/getAll");
            return gson.fromJson(res,new TypeToken<List<Invoice>>() {
            }.getType());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Integer insert(Invoice invoice){
        try{
            String res = NetTool.post(baseUrl+"/insert",gson.toJson(invoice));
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

    public static Invoice findById(Integer id){
        try{
            String res = NetTool.post(baseUrl+"/findById",gson.toJson(id));
            return gson.fromJson(res,Invoice.class);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean update(Invoice invoice){
        try{
            String res = NetTool.post(baseUrl+"/update",gson.toJson(invoice));
            return gson.fromJson(res,Boolean.class);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public static List<Invoice> findByUserId(Integer id){
        try{
            String res = NetTool.post(baseUrl+"/findByUserId",gson.toJson(id));
            return gson.fromJson(res,new TypeToken<List<Invoice>>() {
            }.getType());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static List<Invoice> findByCustomerId(Integer id){
        try{
            String res = NetTool.post(baseUrl+"/findByCustomerId",gson.toJson(id));
            return gson.fromJson(res,new TypeToken<List<Invoice>>() {
            }.getType());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static List<Invoice> findByGoodId(Integer id){
        try{
            String res = NetTool.post(baseUrl+"/findByGoodId",gson.toJson(id));
            return gson.fromJson(res,new TypeToken<List<Invoice>>() {
            }.getType());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
