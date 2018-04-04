package com.pentaKill.dao;

import com.pentaKill.domain.CustomerInfoBean;

public interface CustomerLoginMapper {
    public CustomerInfoBean customerLogin_mapper(String customer_name);
}
