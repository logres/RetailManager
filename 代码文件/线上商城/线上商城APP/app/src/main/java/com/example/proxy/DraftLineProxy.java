package com.example.proxy;

import com.example.api.DraftLine;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.util.List;

public class DraftLineProxy {
    private static String baseUrl = NetTool.basebaseurl+"/DraftLine";
    private static Gson gson = new Gson();


    public static List<DraftLine> getAll(){
        try{
            String res = NetTool.get(baseUrl+"/getAll");
            return gson.fromJson(res,new TypeToken<List<DraftLine>>() {
            }.getType());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Integer insert(DraftLine draftLine){
        try{
            String res = NetTool.post(baseUrl+"/insert",gson.toJson(draftLine));
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

    public static DraftLine findById(Integer id){
        try{
            String res = NetTool.post(baseUrl+"/findById",gson.toJson(id));
            return gson.fromJson(res,DraftLine.class);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean update(DraftLine draftLine){
        try{
            String res = NetTool.post(baseUrl+"/update",gson.toJson(draftLine));
            return gson.fromJson(res,Boolean.class);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public static List<DraftLine> findByDraftId(Integer id){
        try{
            String res = NetTool.post(baseUrl+"/findByDraftId",gson.toJson(id));
            return gson.fromJson(res,new TypeToken<List<DraftLine>>() {
            }.getType());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
