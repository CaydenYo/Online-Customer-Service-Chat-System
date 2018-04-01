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
 
    public int searchCustomerInfoFlag_service(int company_id){
        int flag=csViewsCustomerInfoMapper.searchCustomerInfoFlag_mapper(company_id);
        return flag;
    }
    
    public int searchCompanyId_service(int cs_id){
        int company_id=csViewsCustomerInfoMapper.searchCompanyId_mapper(cs_id);
        return company_id;
    }
    
    public CSViewsCustomerInfoBean searchCustomerInfo_service(int customer_id){
        CSViewsCustomerInfoBean temp = csViewsCustomerInfoMapper.searchCustomerInfo_mapper(customer_id);
        return temp;
    }
    
}
