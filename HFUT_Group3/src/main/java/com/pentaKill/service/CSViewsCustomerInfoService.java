package com.pentaKill.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pentaKill.dao.CSViewsCustomerInfoMapper;
import com.pentaKill.domain.CSViewsCustomerInfoBean;
import com.pentaKill.domain.CustomerInfoBean;

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

    public CustomerInfoBean searchCustomerInfoService(int customer_id) {
        CustomerInfoBean temp = csViewsCustomerInfoMapper.searchCustomerInfoMapper(customer_id);
        return temp;
    }
    
    public List<CSViewsCustomerInfoBean> searchCurrentTime(int cs_id){
        return csViewsCustomerInfoMapper.searchCurrentTime(cs_id);
    }

}
