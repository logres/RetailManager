package com.example.proxy;

import com.example.api.Payment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import java.util.List;

public class PaymentProxy {

    private static String baseUrl = NetTool.basebaseurl+"/Payment";
    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    //private static Gson gson = new Gson();

    public static List<Payment> getAll(){
        try{
            String res = NetTool.get(baseUrl+"/getAll");
            return gson.fromJson(res,new TypeToken<List<Payment>>() {
            }.getType());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Integer insert(Payment payment){
        try{
            String res = NetTool.post(baseUrl+"/insert",gson.toJson(payment));
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

    public static Payment findById(Integer id){
        try{
            String res = NetTool.post(baseUrl+"/findById",gson.toJson(id));
            return gson.fromJson(res,Payment.class);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean update(Payment payment){
        try{
            String res = NetTool.post(baseUrl+"/update",gson.toJson(payment));
            return gson.fromJson(res,Boolean.class);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public static List<Payment> findByInvoiceId(Integer id){
        try{
            String res = NetTool.post(baseUrl+"/findByInvoiceId",gson.toJson(id));
            return gson.fromJson(res,new TypeToken<List<Payment>>() {
            }.getType());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
