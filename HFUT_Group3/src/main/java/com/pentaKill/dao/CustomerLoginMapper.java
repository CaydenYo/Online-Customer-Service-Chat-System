package com.pentaKill.dao;

import com.pentaKill.domain.CustomerInfoBean;
import com.pentaKill.domain.CustomerLoginBean;

public interface CustomerLoginMapper {
    public CustomerInfoBean customerLoginMapper(String customer_name);
    
    public CustomerLoginBean selectCustomerInfo(String customer_email);
}
