package com.example.proxy;

import com.example.api.InvoiceLine;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.util.List;

public class InvoiceLineProxy {
    private static String baseUrl = NetTool.basebaseurl+"/InvoiceLine";
    private static Gson gson = new Gson();


    public static List<InvoiceLine> getAll(){
        try{
            String res = NetTool.get(baseUrl+"/getAll");
            return gson.fromJson(res,new TypeToken<List<InvoiceLine>>() {
            }.getType());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Integer insert(InvoiceLine invoiceLine){
        try{
            String res = NetTool.post(baseUrl+"/insert",gson.toJson(invoiceLine));
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

    public static InvoiceLine findById(Integer id){
        try{
            String res = NetTool.post(baseUrl+"/findById",gson.toJson(id));
            return gson.fromJson(res,InvoiceLine.class);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean update(InvoiceLine invoiceLine){
        try{
            String res = NetTool.post(baseUrl+"/update",gson.toJson(invoiceLine));
            return gson.fromJson(res,Boolean.class);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public static List<InvoiceLine> findByInvoiceId(Integer id){
        try{
            String res = NetTool.post(baseUrl+"/findByInvoiceId",gson.toJson(id));
            return gson.fromJson(res,new TypeToken<List<InvoiceLine>>() {
            }.getType());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static List<InvoiceLine> findByGoodId(Integer id){
        try{
            String res = NetTool.post(baseUrl+"/findByGoodId",gson.toJson(id));
            return gson.fromJson(res,new TypeToken<List<InvoiceLine>>() {
            }.getType());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
