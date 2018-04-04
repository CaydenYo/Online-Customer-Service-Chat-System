package com.pentaKill.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pentaKill.dao.CustomerLoginMapper;
import com.pentaKill.domain.CustomerInfoBean;

@Service
@Scope
public class CustomerLoginService {

    @Resource
    CustomerLoginMapper customerLoginMapper;

    public CustomerInfoBean customerLogin_service(String customer_name) {
        CustomerInfoBean clb = customerLoginMapper.customerLogin_mapper(customer_name);
        return clb;
    }

}
