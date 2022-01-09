package com.hit.logres.phoneproject.provider.controller;

import com.hit.logres.phoneproject.api.entity.NetCustomer;
import com.hit.logres.phoneproject.provider.mapper.NetCustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/NetCustomer")
public class NetCustomerController {

    @Autowired
    NetCustomerMapper netCustomerMapper;


    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    Integer insertNetCustomer(@RequestBody NetCustomer netCustomer){
        try{
            netCustomerMapper.insertNetCustomer(netCustomer);
            return netCustomer.getId();
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    Boolean deleteNetCustomer(@RequestBody Integer id){
        try{
            netCustomerMapper.deleteNetCustomer(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    NetCustomer findNetCustomerById(@RequestBody Integer id){
        try{
            return netCustomerMapper.findNetCustomerById(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    List<NetCustomer> getAllNetCustomer(){
        try{
            return netCustomerMapper.getAllNetCustomer();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    Boolean updateNetCustomer(@RequestBody NetCustomer netCustomer){
        try{
            netCustomerMapper.updateNetCustomer(netCustomer);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/findByUsername",method = RequestMethod.POST)
    List<NetCustomer> findNetCustomerByUsername(@RequestBody String username){
        try{
            return netCustomerMapper.findNetCustomerByUsername(username);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
