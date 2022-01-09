package com.example.proxy;

import com.example.api.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class UserProxy {
    private static String baseUrl = NetTool.basebaseurl+"/User";
    private static Gson gson = new Gson();


    public static List<User> getAll(){
        try{
            String res = NetTool.get(baseUrl+"/getAll");
            return gson.fromJson(res,new TypeToken<List<User>>() {
            }.getType());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Integer insert(User user){
        try{
            String res = NetTool.post(baseUrl+"/insert",gson.toJson(user));
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

    public static User findById(Integer id){
        try{
            String res = NetTool.post(baseUrl+"/findById",gson.toJson(id));
            return gson.fromJson(res,User.class);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean update(User user){
        try{
            String res = NetTool.post(baseUrl+"/update",gson.toJson(user));
            return gson.fromJson(res,Boolean.class);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public static List<User> findByName(String name){
        try{
            String res = NetTool.post(baseUrl+"/findByName",gson.toJson(name));
            return gson.fromJson(res,new TypeToken<List<User>>() {
            }.getType());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
