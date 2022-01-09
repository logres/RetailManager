package com.example.proxy;

import com.example.api.Customer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.math.BigDecimal;
import java.util.List;

public class CustomerProxy {
    private static String baseUrl = NetTool.basebaseurl+"/Customer";
    private static Gson gson = new Gson();

    public static Integer insert(Customer customer){
        try{
            String res = NetTool.post(baseUrl+"/insert",gson.toJson(customer));
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

    public static Customer findById(Integer id){
        try{
            String res = NetTool.post(baseUrl+"/findById",gson.toJson(id));
            return gson.fromJson(res,Customer.class);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static List<Customer> getAll(){
        try{
            String res = NetTool.get(baseUrl+"/getAll");
            return gson.fromJson(res,new TypeToken<List<Customer>>() {
            }.getType());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean update(Customer customer){
        try{
            String res = NetTool.post(baseUrl+"/update",gson.toJson(customer));
            return gson.fromJson(res,Boolean.class);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static List<Customer> findByName(String name){
        try{
            String res = NetTool.post(baseUrl+"/findByName",gson.toJson(name));
            return gson.fromJson(res,new TypeToken<List<Customer>>() {
            }.getType());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String args[]) {
        Customer customer = new Customer(0,"Luo","110","Home","零售","无");
        Integer id = CustomerProxy.insert(customer);
        System.out.println(id);

        Customer new_customer = CustomerProxy.findById(id);
        System.out.println(new_customer.getName());

        customer.setName("新水蜜桃");
        customer.setId(id);
        Boolean res = CustomerProxy.update(customer);
        System.out.println(res);

        List<Customer> llList=CustomerProxy.getAll();
        for ( Customer g:llList){
            System.out.println(g.getName());
        }

        Boolean res2 = CustomerProxy.delete(id);
        System.out.println(res2);

        llList=CustomerProxy.getAll();
        for ( Customer g:llList){
            System.out.println(g.getName());
        }
    }
}
