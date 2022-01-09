package com.hit.logres.phoneproject.provider.mapper;

import com.hit.logres.phoneproject.api.entity.NetCustomer;

import java.util.List;

public interface NetCustomerMapper {
    void insertNetCustomer(NetCustomer netCustomer) throws Exception;

    void deleteNetCustomer(Integer id) throws Exception;

    NetCustomer findNetCustomerById(Integer id) throws Exception;

    List<NetCustomer> getAllNetCustomer() throws Exception;

    void updateNetCustomer(NetCustomer netCustomer) throws Exception;

    List<NetCustomer> findNetCustomerByUsername(String username) throws Exception;
}
