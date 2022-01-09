package com.example.proxy;

import com.example.api.Draft;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.util.List;

public class DraftProxy {
    private static String baseUrl = NetTool.basebaseurl+"/Draft";
    private static Gson gson = new Gson();


    public static List<Draft> getAll(){
        try{
            String res = NetTool.get(baseUrl+"/getAll");
            return gson.fromJson(res,new TypeToken<List<Draft>>() {
            }.getType());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Integer insert(Draft draft){
        try{
            String res = NetTool.post(baseUrl+"/insert",gson.toJson(draft));
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

    public static Draft findById(Integer id){
        try{
            String res = NetTool.post(baseUrl+"/findById",gson.toJson(id));
            return gson.fromJson(res,Draft.class);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean update(Draft draft){
        try{
            String res = NetTool.post(baseUrl+"/update",gson.toJson(draft));
            return gson.fromJson(res,Boolean.class);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


}
