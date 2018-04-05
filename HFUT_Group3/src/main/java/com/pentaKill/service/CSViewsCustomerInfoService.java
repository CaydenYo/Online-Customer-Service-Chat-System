package com.pentaKill.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pentaKill.dao.CSViewsCustomerInfoMapper;
import com.pentaKill.domain.CSViewsCustomerInfoBean;

@Service
@Scope
public class CSViewsCustomerInfoService {
    @Resource
    CSViewsCustomerInfoMapper csViewsCustomerInfoMapper;

    public int searchCustomerInfoFlagService(int company_id) {
        int flag = csViewsCustomerInfoMapper.searchCustomerInfoFlagMapper(company_id);
        return flag;
    }

    public int searchCompanyIdService(int cs_id) {
        int company_id = csViewsCustomerInfoMapper.searchCompanyIdMapper(cs_id);
        return company_id;
    }

    public CSViewsCustomerInfoBean searchCustomerInfoService(int customer_id) {
        CSViewsCustomerInfoBean temp = csViewsCustomerInfoMapper.searchCustomerInfoMapper(customer_id);
        return temp;
    }

}
