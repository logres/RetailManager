package com.hit.logres.provider;

import com.hit.logres.api.service.CustomerService;
import com.hit.logres.provider.serviceimplement.CustomerServiceImp;
import org.apache.dubbo.config.annotation.DubboReference;

public class USE {
    @DubboReference
    CustomerServiceImp customerService;

    public void Say(){
        System.out.println(customerService==null);
    }
}
