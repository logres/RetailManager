package com.hit.logres.phoneproject.provider.mapper;


import com.hit.logres.phoneproject.api.entity.Customer;

import java.util.List;

public interface CustomerMapper{

    void insertCustomer(Customer customer) throws Exception;

    void deleteCustomer(Integer id) throws Exception;

    Customer findCustomerById(Integer id) throws Exception;

    List<Customer> getAllCustomer() throws Exception;

    void updateCustomer(Customer customer) throws Exception;

    List<Customer> findCustomerByName(String name) throws Exception;

}
