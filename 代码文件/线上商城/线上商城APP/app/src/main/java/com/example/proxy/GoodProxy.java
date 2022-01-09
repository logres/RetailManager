package com.example.proxy;

import com.example.api.Good;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import okhttp3.*;

import java.math.BigDecimal;
import java.util.List;

public class GoodProxy {

    private static String baseUrl = NetTool.basebaseurl+"/Good";
    private static Gson gson = new Gson();

    public static List<Good> getAll() {
        try{
            String res = NetTool.get(baseUrl+"/getAll");
            List<Good> goodList = gson.fromJson(res, new TypeToken<List<Good>>() {
            }.getType());
            return goodList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Integer insert(Good good){
        try{
            String res = NetTool.post(baseUrl+"/insert",gson.toJson(good));
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

    public static Good findById(Integer id){
        try{
            String res = NetTool.post(baseUrl+"/findById",gson.toJson(id));
            return gson.fromJson(res,Good.class);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean update(Good good){
        try{
            String res = NetTool.post(baseUrl+"/update",gson.toJson(good));
            return gson.fromJson(res,Boolean.class);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static List<Good> findByName(String name){
        try{
            String res = NetTool.post(baseUrl+"/delete",gson.toJson(name));
            return gson.fromJson(res,new TypeToken<List<Good>>() {
            }.getType());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String args[]) {
        Good good = new Good(0,"水蜜桃","在售", BigDecimal.ONE,BigDecimal.ONE
        ,BigDecimal.ONE,BigDecimal.ONE,1,"#","101");
        Integer id = GoodProxy.insert(good);
        System.out.println(id);

        Good new_good = GoodProxy.findById(id);
        System.out.println(new_good.getName());

        good.setName("新水蜜桃");
        good.setId(id);
        Boolean res = GoodProxy.update(good);
        System.out.println(res);

        List<Good> goodList=GoodProxy.getAll();
        for ( Good g:goodList){
            System.out.println(g.getName());
        }

        Boolean res2 = GoodProxy.delete(id);
        System.out.println(res2);

        goodList=GoodProxy.getAll();
        for ( Good g:goodList){
            System.out.println(g.getName());
        }
    }
}
