package com.pentaKill.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pentaKill.dao.CustomerLoginMapper;
import com.pentaKill.domain.CustomerInfoBean;
import com.pentaKill.domain.CustomerLoginBean;

@Service
@Scope
public class CustomerLoginService {

    @Resource
    CustomerLoginMapper customerLoginMapper;

    public CustomerInfoBean customerLoginService(String customer_name) {
        CustomerInfoBean clb = customerLoginMapper.customerLoginMapper(customer_name);
        return clb;
    }
    
    public CustomerLoginBean selectCustomerInfo(String customer_email){
        CustomerLoginBean clb = customerLoginMapper.selectCustomerInfo(customer_email);
        return clb;
    }

}
