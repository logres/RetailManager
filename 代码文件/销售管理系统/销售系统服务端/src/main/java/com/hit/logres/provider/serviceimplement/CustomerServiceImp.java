package com.hit.logres.provider.serviceimplement;

import com.hit.logres.api.entity.Customer;
import com.hit.logres.provider.mapper.CustomerMapper;
import com.hit.logres.api.service.CustomerService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service("CustomerService")
@DubboService(interfaceClass = CustomerService.class)
public class CustomerServiceImp implements CustomerService {

    @Autowired
    CustomerMapper customerMapper;

    @Override
    public List<Customer> getAllCustomer() {
        try {
            return customerMapper.getAllCustomer();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insertCustomer(Customer customer) {
        try {
            customerMapper.insertCustomer(customer);
            return customer.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Boolean deleteCustomer(Integer id) {
        try {
            customerMapper.deleteCustomer(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Customer findCustomerById(Integer id) {
        try {
            return customerMapper.findCustomerById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean updateCustomer(Customer customer) {
        try {
            customerMapper.updateCustomer(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Customer> findCustomerByName(String name) {
        try {
            return customerMapper.findCustomerByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
