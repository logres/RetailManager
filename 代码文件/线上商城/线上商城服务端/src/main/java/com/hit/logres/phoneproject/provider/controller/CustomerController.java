package com.hit.logres.phoneproject.provider.controller;


import com.hit.logres.phoneproject.api.entity.Customer;
import com.hit.logres.phoneproject.provider.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/Customer")
public class CustomerController {

    @Autowired
    private CustomerMapper customerMapper;

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    Integer insertCustomer(@RequestBody Customer customer){
        try{
            customerMapper.insertCustomer(customer);
            return customer.getId();
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    Boolean deleteCustomer(@RequestBody Integer id){
        try{
            customerMapper.deleteCustomer(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    Customer findCustomerById(@RequestBody Integer id){
        try{
            return customerMapper.findCustomerById(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    List<Customer> getAllCustomer(){
        try{
            return customerMapper.getAllCustomer();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    Boolean updateCustomer(@RequestBody Customer customer){
        try{
            customerMapper.updateCustomer(customer);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/findByName",method = RequestMethod.POST)
    List<Customer> findCustomerByName(@RequestBody String name){
        try{
            return customerMapper.findCustomerByName(name);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
